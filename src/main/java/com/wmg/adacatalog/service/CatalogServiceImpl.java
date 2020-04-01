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
    public List<Catalog> getAll() {
        return catalogRepository.findAll();
    }

    @Override
    public List<Catalog> getByGpid(String gpid) {
        return catalogRepository.findByGpid(gpid);
    }

    @Override
    public List<Catalog> getByLabel(String type, String label) {
        return catalogRepository.findByLabel(type, label);
    }

}
