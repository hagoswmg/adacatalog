package com.wmg.adacatalog.controller;

import com.wmg.adacatalog.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

import static com.wmg.adacatalog.Constants.*;

@RestController
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    /**
     * @param type - the type to search
     * @return a collection of catalog records for a given search by type
     */
    @GetMapping(ADACATALOG)
    public ResponseEntity<?> getRecentSet(@RequestParam(defaultValue = "EntryPage") String type) {
        if (type.equalsIgnoreCase(ENTRY_PAGE)) {
            // collection of recent list (entry page)
            return ResponseEntity.ok(catalogService.getRecentEntrySet());
        }

        if (type.equalsIgnoreCase(ALL)) {
            return ResponseEntity.ok("Success - Search result for all");
        } else if (type.equalsIgnoreCase(GPID)) {
            return ResponseEntity.ok("Success - Search result for GPID");
        } else if (type.equalsIgnoreCase(MAJOR_LABEL)) {
            return ResponseEntity.ok("Success - Search result for MajorLabel");
        } else if (type.equalsIgnoreCase(MARKETING_LABEL)) {
            return ResponseEntity.ok("Success - Search result for MarketingLabel");
        } else if (type.equalsIgnoreCase(PRESENTATION_LABEL)) {
            return ResponseEntity.ok("Success - Search result for PresentationLabel");
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
