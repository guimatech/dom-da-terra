package io.github.guimatech.domdaterra.application.service.kanban;

import io.github.guimatech.domdaterra.domain.kanban.Task;
import io.github.guimatech.domdaterra.domain.kanban.TaskStatus;
import io.github.guimatech.domdaterra.infra.out.db.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public Page<Task> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Task> findAllByStatusNotFiled(Pageable pageable) {
        return repository.findAllByStatusNot(TaskStatus.FILED, pageable);
    }

    public Task findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Task save(Task task) {
        return repository.save(task);
    }

    public void deleteById(Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
    }
}
