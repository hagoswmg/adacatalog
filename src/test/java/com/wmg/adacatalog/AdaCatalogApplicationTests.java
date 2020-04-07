package com.wmg.adacatalog;

import com.wmg.adacatalog.controller.CatalogController;
import com.wmg.adacatalog.controller.OpenplayProductController;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class AdaCatalogApplicationTests {

	private CatalogController catalogController;

	private OpenplayProductController openplayProductController;

	@Autowired
	AdaCatalogApplicationTests(CatalogController catalogController, OpenplayProductController openplayProductController) {
		this.catalogController = catalogController;
		this.openplayProductController = openplayProductController;
	}

	/**
	 * test controllers are alive
	 */
	@Test
	public void contextLoads() {
		Assert.assertNotNull(catalogController);
		Assert.assertNotNull(openplayProductController);
	}

}
