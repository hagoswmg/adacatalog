package com.wmg.adacatalog.controller;

import com.wmg.adacatalog.Utils;
import com.wmg.adacatalog.model.Catalog;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class CatalogControllerTest {

    @Autowired
    private CatalogController catalogController;

    @Test
    void getEntryPage() {
        ResponseEntity<?> response = catalogController.getEntryPage();
        Assertions.assertThat(response.getStatusCode().is2xxSuccessful());
        Assertions.assertThat(response.getBody()).isNotNull();
    }

    @Test
    void searchAll() {
        ResponseEntity<?> response = catalogController.search("ALL", "Warner");
        Assertions.assertThat(response.getStatusCode().is2xxSuccessful());

        if (response.getStatusCode().is2xxSuccessful()) {
            Assertions.assertThat(response.getBody()).isNotNull();
            List<Catalog> list = (List<Catalog>) response.getBody();

            String marketing_label = Utils.trimString(list.get(0).getMarketing_label());
            String presentation_label = Utils.trimString(list.get(0).getPresentation_label());
            String wea_labelcode = Utils.trimString(list.get(0).getWea_labelcode());

            String label = (marketing_label == null ? "" : marketing_label) +
                    (presentation_label == null ? "" : presentation_label) +
                    (wea_labelcode == null ? "" : wea_labelcode);

            Assertions.assertThat(label.matches("Warner"));
        }
    }

    @Test
    void searchGpid() {
        ResponseEntity<?> response = catalogController.search("GPID", "054391946607");
        Assertions.assertThat(response.getStatusCode().is2xxSuccessful());
        if (response.getStatusCode().is2xxSuccessful()) {
            Assertions.assertThat(response.getBody()).isNotNull();
            List<Catalog> list = (List<Catalog>) response.getBody();
            assert list != null;
            Assertions.assertThat(list.get(0).getGpid().equalsIgnoreCase("054391946607"));
        }
    }

    @Test
    void searchWeaLabel() {
        ResponseEntity<?> response = catalogController.search("WeaLabel", "LAT");
        Assertions.assertThat(response.getStatusCode().is2xxSuccessful());
        if (response.getStatusCode().is2xxSuccessful()) {
            Assertions.assertThat(response.getBody()).isNotNull();
            List<Catalog> list = (List<Catalog>) response.getBody();
            assert list != null;
            Assertions.assertThat(list.get(0).getWea_labelcode().matches("LAT"));
        }
    }

    @Test
    void searchMarketingLabel() {
        ResponseEntity<?> response = catalogController.search("MarketingLabel", "Reprise");
        Assertions.assertThat(response.getStatusCode().is2xxSuccessful());
        if (response.getStatusCode().is2xxSuccessful()) {
            Assertions.assertThat(response.getBody()).isNotNull();
            List<Catalog> list = (List<Catalog>) response.getBody();
            assert list != null;
            Assertions.assertThat(list.get(0).getWea_labelcode().matches("Reprise"));
        }
    }

    @Test
    void searchPresentationLabel() {
        ResponseEntity<?> response = catalogController.search("PresentationLabel", "Warner Records");
        Assertions.assertThat(response.getStatusCode().is2xxSuccessful());
        if (response.getStatusCode().is2xxSuccessful()) {
            Assertions.assertThat(response.getBody()).isNotNull();
            List<Catalog> list = (List<Catalog>) response.getBody();
            assert list != null;
            Assertions.assertThat(list.get(0).getWea_labelcode().matches("Warner Records"));
        }
    }


    @Test
    void getDetailsByConfirmationCode() {
        ResponseEntity<?> response = catalogController.getDetailsByConfirmationCode("120");
        Assertions.assertThat(response.getStatusCode().is2xxSuccessful());
        Assertions.assertThat(response.getBody()).isNotNull();
    }

    @Test
    void uploadSearch() {
        String payload = "[\"054391946607\",\"A10302B00002105136\",\"603497175369\",\"A10302B0000096277V\"]";
        ResponseEntity<?> response = catalogController.uploadSearch(payload);
        Assertions.assertThat(response.getStatusCode().is2xxSuccessful());
        if (response.getStatusCode().is2xxSuccessful()) {
            Assertions.assertThat(response.getBody()).isNotNull();
            List<Catalog> list = (List<Catalog>) response.getBody();
            assert list != null;
            Assertions.assertThat(list.get(0).getGpid().matches("054391946607"));
        }
    }
}