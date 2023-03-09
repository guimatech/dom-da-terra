package io.github.guimatech.domdaterra.application.service.kanban;

import io.github.guimatech.domdaterra.domain.kanban.Project;
import io.github.guimatech.domdaterra.infra.out.db.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class ProjectService {

    @Autowired
    private ProjectRepository repository;

    public Page<Project> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Project> findAllByActiveTrue() {
        return repository.findAllByActiveTrue();
    }

    public Project findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Project save(Project project) {
        return repository.save(project);
    }

    public void deleteById(Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
    }
}
