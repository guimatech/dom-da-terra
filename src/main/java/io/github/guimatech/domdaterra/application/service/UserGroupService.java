package io.github.guimatech.domdaterra.application.service;

import io.github.guimatech.domdaterra.domain.UserGroup;
import io.github.guimatech.domdaterra.infra.out.db.repository.UserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class UserGroupService {

    @Autowired
    private UserGroupRepository repository;

    public Page<UserGroup> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<UserGroup> findAllByActiveTrue() {
        return repository.findAllByActiveTrue();
    }

    public UserGroup findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public UserGroup save(UserGroup userGroup) {
        return repository.save(userGroup);
    }

    public void deleteById(Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
    }
}
