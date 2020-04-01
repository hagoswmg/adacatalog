package com.wmg.adacatalog.repository;

import com.wmg.adacatalog.model.Catalog;

import java.util.List;

public interface CatalogRepository {

    String SELECT_STMT = "SELECT GPID, ARTIST, TITLE, WEA_LABELCODE, MARKETING_LABEL, PRESENTATION_LABEL, GENRE,FORMAT_CODE, MEDIA_CODE, PA_FLAG, STREET_DATE, INTERNAL_COMMENTS FROM ACTUAL_CONFIGS ";

    List<Catalog> findAll();

    List<Catalog> findByGpid(String gpid);

    List<Catalog> findByLabel(String type, String label);
}
