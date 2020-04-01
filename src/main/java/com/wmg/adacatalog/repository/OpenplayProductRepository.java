package com.wmg.adacatalog.repository;

import com.wmg.adacatalog.model.OpenplayProduct;
import org.springframework.data.repository.CrudRepository;

public interface OpenplayProductRepository extends CrudRepository<OpenplayProduct, Long> {
    Iterable<OpenplayProduct> findTop10ByOrderByLastUpdateDateDesc();
}
