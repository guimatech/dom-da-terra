package io.github.guimatech.domdaterra.billingrobot.application.service;

import io.github.guimatech.domdaterra.billingrobot.domain.Billing;
import io.github.guimatech.domdaterra.billingrobot.infra.out.db.repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
@Validated
public class BillingService {

    @Autowired
    private BillingRepository repository;

    public List<Billing> findByCurrentDate() {
        return repository.findByCurrentDate();
    }

    public List<Billing> saveAll(List<@Valid Billing> billings) {
        repository.deleteAll();
        return StreamSupport.stream(repository.saveAll(billings).spliterator(), false).toList();
    }

    public Page<Billing> findAll(Pageable pageable) {
        return repository.findAllByOrderByIdAsc(pageable);
    }

    public Billing findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Billing save(Billing billing) {
        return repository.save(billing);
    }

    public void deleteById(Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
    }
}
