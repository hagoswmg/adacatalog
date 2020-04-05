package com.wmg.adacatalog.service;

import com.wmg.adacatalog.model.Catalog;

import java.util.List;

public interface CatalogService {

    List<Catalog> getAll(String input);

    List<Catalog> getByGpid(List<String> gpidList);

    List<Catalog> getByLabel(String type, String label);

    List<String> getBySetId(String setId);
}
