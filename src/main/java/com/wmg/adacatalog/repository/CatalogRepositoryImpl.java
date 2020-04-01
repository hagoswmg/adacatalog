package com.wmg.adacatalog.repository;

import com.wmg.adacatalog.Constants;
import com.wmg.adacatalog.model.Catalog;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public class CatalogRepositoryImpl implements CatalogRepository {

    @PersistenceContext
    EntityManager entityManager;

    /**
     * @return a list of all searched items in the catalog
     */
    @Override
    public List<Catalog> findAll() {
        final String SQL_QUERY = SELECT_STMT + " WHERE ROWNUM < 21";
        Query nativeQuery = entityManager.createNativeQuery(SQL_QUERY, "Catalog");

        return nativeQuery.getResultList();
    }

    /**
     * @param gpid - comma separted gpid list
     * @return - collection of product items for the given gpids
     */
    @Override
    public List<Catalog> findByGpid(String gpid) {
        if (gpid != null && !gpid.trim().equals("")) {
            String[] split = gpid.split(",");
        }

        final String SQL_QUERY = SELECT_STMT + " WHERE GPID=?";

        Query nativeQuery = entityManager.createNativeQuery(SQL_QUERY, "Catalog");
        nativeQuery.setParameter(1, gpid);
        return nativeQuery.getResultList();
    }

    /**
     * @param type  - the label type (WEA Label, Marketing Label, or Presentation Label)
     * @param label - the value for label
     * @return - a collection of products for the given label
     */
    @Override
    public List<Catalog> findByLabel(String type, String label) {
        String SQL_QUERY = SELECT_STMT;
        if (type.equalsIgnoreCase(Constants.WEA_LABEL)) {
            SQL_QUERY += " WHERE WEA_LABELCODE like '%" + label + "%' AND ROWNUM < 21";
        } else if (type.equalsIgnoreCase(Constants.MARKETING_LABEL)) {
            SQL_QUERY += " WHERE MARKETING_LABEL like '%" + label + "%' AND ROWNUM < 21";
        } else if (type.equalsIgnoreCase(Constants.PRESENTATION_LABEL)) {
            SQL_QUERY += " WHERE PRESENTATION_LABEL like '%" + label + "%' AND ROWNUM < 21";
        }

        Query nativeQuery = entityManager.createNativeQuery(SQL_QUERY, "Catalog");
        return nativeQuery.getResultList();
    }

}
