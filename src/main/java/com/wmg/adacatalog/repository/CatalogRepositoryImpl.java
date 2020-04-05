package com.wmg.adacatalog.repository;

import com.wmg.adacatalog.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
@Slf4j
public class CatalogRepositoryImpl implements CatalogRepository {

    private EntityManager entityManager;

    public CatalogRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * @param label - the label to search
     * @return a collection of assets found for the label
     * @TODO remove the row limit
     */
    @Override
    public List findAll(String label) {

        String WHERE_CLAUSE = " WHERE ROWNUM < 21 AND (" +
                "WEA_LABELCODE like :weaLabel OR " +
                "MARKETING_LABEL like :marketingLabel OR " +
                "PRESENTATION_LABEL like :presentationLabel )";

        final String SQL_QUERY = SEARCH_SELECT_STMT + WHERE_CLAUSE;

        log.info("findAll_SQL_QUERY=" + SQL_QUERY);
        log.info("findAll_SQL_QUERY_label=" + label);

        Query nativeQuery = entityManager.createNativeQuery(SQL_QUERY, "Catalog");
        nativeQuery.setParameter("weaLabel", "%" + label + "%");
        nativeQuery.setParameter("marketingLabel", "%" + label + "%");
        nativeQuery.setParameter("presentationLabel", "%" + label + "%");

        return nativeQuery.getResultList();
    }

    /**
     * @param gpidList - list of gpids
     * @return - collection of product items for the given gpids
     */
    @Override
    public List findByGpid(List<String> gpidList) {
        final String SQL_QUERY = SEARCH_SELECT_STMT + " WHERE GPID IN :gpids";
        log.info("findByGpid_SQL_QUERY=" + SQL_QUERY);
        log.info("findByGpid_SQL_QUERY_GPID_LIST=" + gpidList);

        Query nativeQuery = entityManager.createNativeQuery(SQL_QUERY, "Catalog");
        nativeQuery.setParameter("gpids", gpidList);

        return nativeQuery.getResultList();
    }

    /**
     * @param type  - the label type (WEA Label, Marketing Label, or Presentation Label)
     * @param label - the value for label
     * @return - a collection of products for the given label
     * @TODO remove the row limit
     */
    @Override
    public List findByLabel(String type, String label) {
        String SQL_QUERY = SEARCH_SELECT_STMT;
        if (type.equalsIgnoreCase(Constants.WEA_LABEL)) {
            SQL_QUERY += " WHERE WEA_LABELCODE like :weaLabel AND ROWNUM < 21";
            Query nativeQuery = entityManager.createNativeQuery(SQL_QUERY, "Catalog");
            nativeQuery.setParameter("weaLabel", "%" + label + "%");
            log.info("findByLabel_SQL_QUERY=" + SQL_QUERY);
            log.info("findByLabel_SQL_QUERY_label=" + label);

            return nativeQuery.getResultList();
        } else if (type.equalsIgnoreCase(Constants.MARKETING_LABEL)) {
            SQL_QUERY += " WHERE MARKETING_LABEL like :marketingLabel AND ROWNUM < 21";
            Query nativeQuery = entityManager.createNativeQuery(SQL_QUERY, "Catalog");
            nativeQuery.setParameter("marketingLabel", "%" + label + "%");
            log.info("findByLabel_SQL_QUERY=" + SQL_QUERY);
            log.info("findByLabel_SQL_QUERY_label=" + label);

            return nativeQuery.getResultList();

        } else if (type.equalsIgnoreCase(Constants.PRESENTATION_LABEL)) {
            SQL_QUERY += " WHERE PRESENTATION_LABEL like :presentationLabel AND ROWNUM < 21";
            Query nativeQuery = entityManager.createNativeQuery(SQL_QUERY, "Catalog");
            nativeQuery.setParameter("presentationLabel", "%" + label + "%");
            log.info("findByLabel_SQL_QUERY=" + SQL_QUERY);
            log.info("findByLabel_SQL_QUERY_label=" + label);

            return nativeQuery.getResultList();
        }

        return null;
    }

    @Override
    public List findBySetId(String setId) {
        String SQL_QUERY = DRILL_DOWN_SEARCH_STMT + " WHERE SET_ID=:setid";
        Query nativeQuery = entityManager.createNativeQuery(SQL_QUERY);
        nativeQuery.setParameter("setid", setId);

        return nativeQuery.getResultList();
    }

}
