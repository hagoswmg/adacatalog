package com.wmg.adacatalog.repository;

import com.wmg.adacatalog.model.Catalog;

import java.util.List;

public interface CatalogRepository {

    String SEARCH_SELECT_STMT = "SELECT GPID,ARTIST,TITLE,WEA_LABELCODE,MARKETING_LABEL,PRESENTATION_LABEL,GENRE,FORMAT_CODE,MEDIA_CODE,PA_FLAG,STREET_DATE,INTERNAL_COMMENTS FROM ACTUAL_CONFIGS";

    String DRILL_DOWN_SEARCH_STMT = "SELECT IDENTIFIER FROM OPENPLAY_DETAIL";

    List<Catalog> findAll(String input);

    List<Catalog> findByGpid(List<String> gpidList);

    List<Catalog> findByLabel(String type, String label);

    List<String> findBySetId(String setId);
}
