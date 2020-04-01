package com.wmg.adacatalog.controller;

import com.wmg.adacatalog.service.CatalogService;
import com.wmg.adacatalog.service.OpenplayProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

import static com.wmg.adacatalog.Constants.*;

@RestController
public class CatalogController {

    private CatalogService catalogService;

    private OpenplayProductService openplayProductService;

    public CatalogController(CatalogService catalogService, OpenplayProductService openplayProductService) {
        this.catalogService = catalogService;
        this.openplayProductService = openplayProductService;
    }

    /**
     * @param type  - the type to search (GPID/ALL/MarketingLabel etc)
     * @param value - (optional) the value for type (eg GPID = 1234, MarketingLabel = WM US, etc)
     * @return a collection of catalog records for a given search by type
     */
    @GetMapping(ADACATALOG)
    public ResponseEntity<?> getRecentSet(@RequestParam(defaultValue = "EntryPage")
                                                  String type, @RequestParam(required = false) String value) {
        if (type.equalsIgnoreCase(ENTRY_PAGE)) {
            // collection of recent list (entry page)
            return ResponseEntity.ok(openplayProductService.getEntryPage());
        }

        if (type.equalsIgnoreCase(ALL)) {
            // ALL
            return ResponseEntity.ok(catalogService.getAll());

        } else if (type.equalsIgnoreCase(GPID)) {
            // GPID
            return ResponseEntity.ok(catalogService.getByGpid(value));
        } else if (type.equalsIgnoreCase(WEA_LABEL) || type.equalsIgnoreCase(MARKETING_LABEL)
                || type.equalsIgnoreCase(PRESENTATION_LABEL)) {
            // Wea Label or Marketing Label or Presentation Label
            return ResponseEntity.ok(catalogService.getByLabel(type, value));

        } else if (type.equalsIgnoreCase(UPLOAD)) {
            return ResponseEntity.ok("Success - Search result for Upload");
        } else {
            return ResponseEntity.ok("Failure - Invalid Search Critria");
        }
    }

    /**
     * @param confirmationCode - the confirmation code to look details for
     * @return - The drill down details info for a given entry by confirmation code
     */
    @GetMapping(ADACATALOG_CONFIRMATION_CODE)
    public ResponseEntity<?> getDetailsByConfirmationCode(@PathParam("confirmationCode") String confirmationCode) {
        return ResponseEntity.ok("Success - Details by confirmation code");
    }

    /**
     * @param jsonPayload - the json payload
     * @return - confirmation number for the saved item
     */
    @PostMapping(value = ADACATALOG, consumes = "application/json", produces = "application/json")
    public String createSet(@RequestBody String jsonPayload) {
        return "confirmation code";
    }
}
