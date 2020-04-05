package com.wmg.adacatalog.service;

import com.wmg.adacatalog.model.OpenplayDetail;
import com.wmg.adacatalog.repository.OpenplayDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpenplayDetailServiceImpl implements OpenplayDetailService {

    private OpenplayDetailRepository openplayDetailRepository;

    public OpenplayDetailServiceImpl(OpenplayDetailRepository openplayDetailRepository) {
        this.openplayDetailRepository = openplayDetailRepository;
    }

    @Override
    public void save(OpenplayDetail openplayDetail) {
        openplayDetailRepository.save(openplayDetail);
    }

    @Override
    public void saveAll(List<OpenplayDetail> detailsList) {
        openplayDetailRepository.saveAll(detailsList);
    }

    @Override
    public Long getNextSeriesId() {
        return openplayDetailRepository.findNextSeriesId();
    }

}
