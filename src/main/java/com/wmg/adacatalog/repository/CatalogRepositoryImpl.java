package com.wmg.adacatalog.repository;

import com.wmg.adacatalog.model.RecentSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class CatalogRepositoryImpl implements CatalogRepository {

    @Override
    public List<RecentSet> findRecentEntry() {
        List<RecentSet> recentSet = new ArrayList<>();

        // hard coded for now (should be removed and retrieved from database)
        recentSet.add(new RecentSet("99021", "Lorem Ipsum", "pending", new Date()));
        recentSet.add(new RecentSet("23642", "dolor sit amet", "Pending", new Date()));
        recentSet.add(new RecentSet("52739", "Lorem Ipsum dolor sit amet", "Complete", new Date()));
        recentSet.add(new RecentSet("11220", "Amet sit dolor", "Complete", new Date()));

        return recentSet;
    }

}
