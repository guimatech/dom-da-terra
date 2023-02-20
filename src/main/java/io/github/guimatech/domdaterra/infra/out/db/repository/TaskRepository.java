package io.github.guimatech.domdaterra.infra.out.db.repository;

import io.github.guimatech.domdaterra.domain.kanban.Task;
import io.github.guimatech.domdaterra.domain.kanban.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {

    Page<Task> findAllByStatusNot(TaskStatus status, Pageable pageable);
}
