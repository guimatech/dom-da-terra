package io.github.guimatech.domdaterra.application.service.kanban;

import io.github.guimatech.domdaterra.domain.kanban.Category;
import io.github.guimatech.domdaterra.infra.out.db.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAllByActiveTrue() {
        return repository.findAllByActiveTrue();
    }
}
