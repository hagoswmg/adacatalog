package com.wmg.adacatalog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wmg.adacatalog.Utils;
import com.wmg.adacatalog.model.ErrorMessage;
import com.wmg.adacatalog.service.CatalogService;
import com.wmg.adacatalog.service.OpenplayProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.wmg.adacatalog.Constants.*;

@RestController
@Slf4j
public class CatalogController {

    private CatalogService catalogService;

    private OpenplayProductService openplayProductService;

    public CatalogController(CatalogService catalogService, OpenplayProductService openplayProductService) {
        this.catalogService = catalogService;
        this.openplayProductService = openplayProductService;
    }

    /**
     * @return a collection of recently saved set
     */
    @GetMapping(ADACATALOG)
    public ResponseEntity<?> getEntryPage() {
        log.debug("Entered ADA Catalog Entry Page.");
        return ResponseEntity.ok(openplayProductService.getEntryPage());
    }

    /**
     * @param type  - the type to search (GPID search or Label search etc...)
     * @param value - the value for type (eg GPID = 1234, MarketingLabel = WM US, etc)
     * @return - a collection of catalog records for a given search by type
     */
    @GetMapping(ADACATALOG_SEARCH)
    public ResponseEntity<?> search(@RequestParam String type, @RequestParam String value) {
        log.debug("Search request for type={} and value=\"{}\".", type, value);

        if (Utils.trimString(value) == null) {
            ErrorMessage errorMessage = new ErrorMessage("Error", "Missing search critria");
            log.error(errorMessage.toString());
            return ResponseEntity.badRequest().body(errorMessage);
        }

        if (type.equalsIgnoreCase(ALL)) {
            return ResponseEntity.ok(catalogService.getAll(Utils.trimString(value)));
        } else if (type.equalsIgnoreCase(GPID)) {
            return ResponseEntity.ok(catalogService.getByGpid(Utils.getCleansedString(value, ",")));
        } else { // Wea Label or Marketing Label or Presentation Label
            return ResponseEntity.ok(catalogService.getByLabel(type, value));
        }
    }

    /**
     * @param setId - the confirmation code to look details for
     * @return - The drill down details info for a given entry by set id
     */
    @GetMapping(ADACATALOG_CONFIRMATION_CODE)
    public ResponseEntity<?> getDetailsByConfirmationCode(@PathVariable("setId") String setId) {
        log.debug("Drill down search for setId={}", setId);
        List<String> gpidList = catalogService.getBySetId(setId);
        return ResponseEntity.ok(catalogService.getByGpid(gpidList));
    }

    /**
     * Upload search
     * @param payload - a json array of GIPDs
     * @return a collection for he given GIPDs
     */
    @PostMapping(ADACATALOG_SEARCH)
    public ResponseEntity<?> uploadSearch(@RequestBody String payload) {
        log.debug("Upload search with payload: {}", Utils.trimAndSqueezeWs(payload));
        ObjectMapper mapper = new ObjectMapper();
        try {
            String[] gpids = mapper.readValue(payload, String[].class);
            List<String> uniqueGpidList = Arrays.stream(gpids)
                    .filter(x -> Utils.trimString(x) != null)
                    .map(Utils::trimString)
                    .distinct()
                    .collect(Collectors.toList());

            log.debug("Unique GPID list: {}", uniqueGpidList);
            return ResponseEntity.ok(catalogService.getByGpid(uniqueGpidList));

        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException - parse error - details {} ", e.getMessage());
            return ResponseEntity.badRequest().body(new ErrorMessage("Error", "Bad input data"));
        }
    }
}
