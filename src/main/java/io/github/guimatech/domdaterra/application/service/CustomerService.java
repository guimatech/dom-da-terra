package io.github.guimatech.domdaterra.application.service;

import io.github.guimatech.domdaterra.domain.Customer;
import io.github.guimatech.domdaterra.infra.out.db.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Page<Customer> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Customer> findAllByActiveTrue() {
        return repository.findAllByActiveTrue();
    }

    public Customer findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    public void deleteById(Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
    }
}
