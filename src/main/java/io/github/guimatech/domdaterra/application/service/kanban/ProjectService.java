package io.github.guimatech.domdaterra.application.service.kanban;

import io.github.guimatech.domdaterra.domain.kanban.Project;
import io.github.guimatech.domdaterra.infra.out.db.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class ProjectService {

    @Autowired
    private ProjectRepository repository;

    public List<Project> findAllByActiveTrue() {
        return repository.findAllByActiveTrue();
    }
}
