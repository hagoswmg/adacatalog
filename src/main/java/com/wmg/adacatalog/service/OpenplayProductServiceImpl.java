package com.wmg.adacatalog.service;

import com.wmg.adacatalog.model.OpenplayProduct;
import com.wmg.adacatalog.repository.OpenplayProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OpenplayProductServiceImpl implements OpenplayProductService {

    private OpenplayProductRepository openplayProductRepository;

    public OpenplayProductServiceImpl(OpenplayProductRepository openplayProductRepository) {
        this.openplayProductRepository = openplayProductRepository;
    }

    /**
     * Populates the entry page of ada to openplay backfill request
     * @return a sub list of recently created play set
     */
    @Override
    public List<OpenplayProduct> getEntryPage() {
        List<OpenplayProduct> list = new ArrayList<>();
        openplayProductRepository.findTop10ByOrderByLastUpdateDateDesc().forEach(list::add);

        return list;
    }

    @Override
    public Long getNextSeriesId() {
        return openplayProductRepository.findNextSeriesId();
    }

    @Override
    public OpenplayProduct save(OpenplayProduct openplayProduct) {
        return openplayProductRepository.save(openplayProduct);
    }

    @Override
    public void deleteBySetId(Long setId) {
        openplayProductRepository.deleteById(setId);
    }

    @Override
    public Optional<OpenplayProduct> getBySetId(long setId) {
        return openplayProductRepository.findById(setId);
    }
}
