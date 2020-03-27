package com.wmg.adacatalog.repository;

import com.wmg.adacatalog.model.RecentSet;

import java.util.List;

public interface CatalogRepository {
    List<RecentSet> findRecentEntry();
}
