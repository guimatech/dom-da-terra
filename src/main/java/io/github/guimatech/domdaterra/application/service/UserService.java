package io.github.guimatech.domdaterra.application.service;

import io.github.guimatech.domdaterra.domain.User;
import io.github.guimatech.domdaterra.infra.out.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAllByActiveTrue() {
        return repository.findAllByActiveTrue();
    }
}
