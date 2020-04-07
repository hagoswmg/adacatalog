package com.wmg.adacatalog.controller;

import com.wmg.adacatalog.model.OpenplayProduct;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class OpenplayProductControllerTest {

    private OpenplayProductController openplayProductController;

    @Autowired
    OpenplayProductControllerTest(OpenplayProductController openplayProductController) {
        this.openplayProductController = openplayProductController;
    }

    @Test
    void createSet() {

        String payload = "{\"setName\":\"Regea\",\"createdBy\":\"GH\",\"company\":\"Warner Music\",\"comments\":\"Bob Marley Collection\",\"payload\":[{\"identifier\":\"054391567567\",\"identifier_type_code\":\"GPID\"},{\"identifier\":\"022924496561\",\"identifier_type_code\":\"GPID\"},{\"identifier\":\"022924496523\",\"identifier_type_code\":\"GPID\"},{\"identifier\":\"016861200435\",\"identifier_type_code\":\"GPID\"},{\"identifier\":\"075679953728\",\"identifier_type_code\":\"GPID\"}]}";
        ResponseEntity<?> response = openplayProductController.createSet(payload);
        Assertions.assertThat(response.getStatusCode().is2xxSuccessful());
        if (response.getStatusCode().is2xxSuccessful()) {
            Assertions.assertThat(response.getBody()).isNotNull();
            OpenplayProduct openplayProduct = (OpenplayProduct) response.getBody();

            Assertions.assertThat(openplayProduct.getSetName().equalsIgnoreCase("Regea"));

            openplayProductController.deleteSet(openplayProduct.getSetId());
            ResponseEntity<?> entity = openplayProductController.getBySetId(openplayProduct.getSetId());
            Optional<OpenplayProduct> opp = (Optional<OpenplayProduct>) entity.getBody();
            Assertions.assertThat(opp).isEmpty();
        }
    }
}