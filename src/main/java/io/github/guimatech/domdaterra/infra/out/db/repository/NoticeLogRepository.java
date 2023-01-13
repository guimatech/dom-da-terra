package io.github.guimatech.domdaterra.infra.out.db.repository;

import io.github.guimatech.domdaterra.domain.NoticeLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeLogRepository extends PagingAndSortingRepository<NoticeLog, Long> {
    Page<NoticeLog> findAllByOrderByIdAsc(Pageable pageable);
}
