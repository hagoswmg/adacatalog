package com.wmg.adacatalog.repository;

import com.wmg.adacatalog.model.OpenplayDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpenplayDetailRepository extends CrudRepository<OpenplayDetail, Long> {

    @Query(value = "SELECT DMD.OP_DETAIL_ID_S.nextval from dual", nativeQuery = true)
    Long findNextSeriesId();
}
