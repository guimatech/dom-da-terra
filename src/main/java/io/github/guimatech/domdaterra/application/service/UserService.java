package io.github.guimatech.domdaterra.application.service;

import io.github.guimatech.domdaterra.domain.User;
import io.github.guimatech.domdaterra.infra.out.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class UserService {

    @Autowired
    private UserRepository repository;

    public Page<User> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<User> findAllByActiveTrue() {
        return repository.findAllByActiveTrue();
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public User save(User user) {
        return repository.save(user);
    }

    public void deleteById(Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
    }
}
