package com.wmg.adacatalog.service;

import com.wmg.adacatalog.model.OpenplayProduct;

import java.util.List;
import java.util.Optional;

public interface OpenplayProductService {
    List<OpenplayProduct> getEntryPage();

    Long getNextSeriesId();

    OpenplayProduct save(OpenplayProduct openplayProduct);

    void deleteBySetId(Long setId);

    Optional<OpenplayProduct> getBySetId(long setId);
}
