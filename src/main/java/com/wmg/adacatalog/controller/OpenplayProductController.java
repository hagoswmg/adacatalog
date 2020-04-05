package com.wmg.adacatalog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wmg.adacatalog.Constants;
import com.wmg.adacatalog.Utils;
import com.wmg.adacatalog.model.ErrorMessage;
import com.wmg.adacatalog.model.OpenplayDetail;
import com.wmg.adacatalog.model.OpenplayProduct;
import com.wmg.adacatalog.service.OpenplayDetailService;
import com.wmg.adacatalog.service.OpenplayProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.wmg.adacatalog.Constants.ADACATALOG;

@RestController
@Slf4j
public class OpenplayProductController {

    private OpenplayProductService openplayProductService;

    private OpenplayDetailService openplayDetailService;

    public OpenplayProductController(OpenplayProductService openplayProductService,
                                     OpenplayDetailService openplayDetailService) {
        this.openplayProductService = openplayProductService;
        this.openplayDetailService = openplayDetailService;
    }

    /**
     * @param jsonPayload - the json payload
     * @return - the saved item
     */
    @PostMapping(value = ADACATALOG, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createSet(@RequestBody String jsonPayload) {
        log.debug("CREATE_SET request with payload={}", Utils.trimAndSqueezeWs(jsonPayload));
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(jsonPayload);
            Map<String, JsonNode> map = Utils.processJsonNode(node);
            // selected items are in json array and must be named payload
            JsonNode arrNode = node.get("payload");

            if (arrNode.isArray()) {
                OpenplayProduct openplayProduct = saveOpenplayProduct(map);
                saveOpenplayDetail(arrNode, openplayProduct.getSetId());

                return ResponseEntity.ok(openplayProduct);
            } else {
                log.warn("CREATE_SET - Nothing saved no selection was made");
                return ResponseEntity.badRequest().body(new ErrorMessage("Error", "Nothing saved no selection was made"));
            }

        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException - while parsing jsonPayload: ", e);
            return ResponseEntity.badRequest().body(new ErrorMessage("Error", e.getMessage()));
        } catch (ParseException e) {
            log.error("ParseException - while parsing processedDate or lastUpdateDate: ", e);
            return ResponseEntity.badRequest().body(new ErrorMessage("Error", e.getMessage()));
        }
    }

    private void saveOpenplayDetail(JsonNode arrayNode, Long setId) {
        List<OpenplayDetail> detailsList = new ArrayList<>();
        for (JsonNode next : arrayNode) {
            Map<String, JsonNode> stringJsonNodeMap = Utils.processJsonNode(next);
            String identifier = Utils.getAttribute(stringJsonNodeMap, "identifier");
            String identifier_type_code = Utils.getAttribute(stringJsonNodeMap, "identifier_type_code");
            OpenplayDetail openplayDetail = new OpenplayDetail();

            openplayDetail.setDetailId(openplayDetailService.getNextSeriesId());
            openplayDetail.setSetId(setId);
            openplayDetail.setIdentifier(identifier);
            openplayDetail.setIdentifierTypeCode(identifier_type_code);
            openplayDetail.setDealSent(Constants.SENT_STATUS);
            openplayDetail.setPmiSent(Constants.SENT_STATUS);
            openplayDetail.setCoverArtSent(Constants.SENT_STATUS);
            openplayDetail.setAudioSent(Constants.SENT_STATUS);
            openplayDetail.setStatus(Constants.STATUS_PENDING);

            detailsList.add(openplayDetail);
        }
        openplayDetailService.saveAll(detailsList);
        log.info("Saved to database (OPENPLAY_DETAIL table): " + detailsList);
    }

    private OpenplayProduct saveOpenplayProduct(Map<String, JsonNode> map) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
        String processedDate = Utils.getAttribute(map, "processedDate");
        String lastUpdateDate = Utils.getAttribute(map, "lastUpdateDate");
        Long setId = openplayProductService.getNextSeriesId();
        OpenplayProduct openplayProduct = new OpenplayProduct(
                setId,
                Utils.getAttribute(map, "setName"),
                new Timestamp(new Date().getTime()),
                Utils.getAttribute(map, "createdBy"),
                ((processedDate != null) ? simpleDateFormat.parse(processedDate) : null),
                ((lastUpdateDate != null) ? simpleDateFormat.parse(lastUpdateDate) : null),
                Constants.STATUS_PENDING,
                Utils.getAttribute(map, "directory"),
                Utils.getAttribute(map, "company"),
                Utils.getAttribute(map, "comments")
        );
        openplayProductService.save(openplayProduct);
        log.info("Saved to database (OPENPLAY_PRODUCT table): " + openplayProduct.toString());

        return openplayProduct;
    }
}
