package com.wmg.adacatalog.service;

import com.wmg.adacatalog.model.RecentSet;
import com.wmg.adacatalog.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CatalogRepository catalogRepository;

    @Override
    public List<RecentSet> getRecentEntrySet() {
        return catalogRepository.findRecentEntry();
    }
}
