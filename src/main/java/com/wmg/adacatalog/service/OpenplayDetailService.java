package com.wmg.adacatalog.service;

import com.wmg.adacatalog.model.OpenplayDetail;

import java.util.List;

public interface OpenplayDetailService {
    void save(OpenplayDetail openplayDetail);

    void saveAll(List<OpenplayDetail> detailsList);

    Long getNextSeriesId();
}
