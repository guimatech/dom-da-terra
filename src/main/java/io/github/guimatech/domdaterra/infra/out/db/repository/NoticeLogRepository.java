package io.github.guimatech.domdaterra.infra.out.db.repository;

import io.github.guimatech.domdaterra.domain.NoticeLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeLogRepository extends PagingAndSortingRepository<NoticeLog, Long> {
    Page<NoticeLog> findAllByOrderByIdAsc(Pageable pageable);

    @Query("SELECT nl FROM NoticeLog nl WHERE CURRENT_DATE BETWEEN nl.startDatePeriod AND nl.endDatePeriod")
    List<NoticeLog> findAllByCurrentDate();
}
