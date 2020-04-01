package com.wmg.adacatalog.service;

import com.wmg.adacatalog.model.Catalog;

import java.util.List;

public interface CatalogService {

    List<Catalog> getAll();

    List<Catalog> getByGpid(String gpid);

    List<Catalog> getByLabel(String type, String label);

}
