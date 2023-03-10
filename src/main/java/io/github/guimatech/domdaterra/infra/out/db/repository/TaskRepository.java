package io.github.guimatech.domdaterra.infra.out.db.repository;

import io.github.guimatech.domdaterra.domain.kanban.Task;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {

    List<Task> findAllByActiveTrue();
}
