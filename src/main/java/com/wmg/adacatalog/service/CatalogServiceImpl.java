package com.wmg.adacatalog.service;

import com.wmg.adacatalog.model.Catalog;
import com.wmg.adacatalog.repository.CatalogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    private CatalogRepository catalogRepository;

    public CatalogServiceImpl(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Override
    public List<Catalog> getAll(String input) {
        return catalogRepository.findAll(input);
    }

    @Override
    public List<Catalog> getByGpid(List<String> gpidList) {
        return catalogRepository.findByGpid(gpidList);
    }

    @Override
    public List<Catalog> getByLabel(String type, String label) {
        return catalogRepository.findByLabel(type, label);
    }

    @Override
    public List<String> getBySetId(String setId) {
        return catalogRepository.findBySetId(setId);
    }

}
