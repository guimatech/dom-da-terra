package io.github.guimatech.domdaterra.application.service.kanban;

import io.github.guimatech.domdaterra.domain.kanban.Category;
import io.github.guimatech.domdaterra.infra.out.db.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Page<Category> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Category> findAllByActiveTrue() {
        return repository.findAllByActiveTrue();
    }

    public Category findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Category save(Category category) {
        return repository.save(category);
    }

    public void deleteById(Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
    }
}
