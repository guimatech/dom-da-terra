package io.github.guimatech.domdaterra.application.service;

import io.github.guimatech.domdaterra.domain.Customer;
import io.github.guimatech.domdaterra.infra.out.db.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public List<Customer> findAllByActiveTrue() {
        return repository.findAllByActiveTrue();
    }
}
