package com.wmg.adacatalog.repository;

import com.wmg.adacatalog.model.OpenplayProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OpenplayProductRepository extends CrudRepository<OpenplayProduct, Long> {
    Iterable<OpenplayProduct> findTop10ByOrderByLastUpdateDateDesc();

    @Query(value = "SELECT DMD.OP_PRODUCT_SET_S.nextval from dual", nativeQuery = true)
    Long findNextSeriesId();
}
