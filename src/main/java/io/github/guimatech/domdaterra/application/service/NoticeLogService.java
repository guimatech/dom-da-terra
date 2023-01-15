package io.github.guimatech.domdaterra.application.service;

import io.github.guimatech.domdaterra.domain.NoticeLog;
import io.github.guimatech.domdaterra.infra.out.db.repository.NoticeLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeLogService {

    @Autowired
    private NoticeLogRepository repository;

    public Page<NoticeLog> findAll(Pageable pageable) {
        return repository.findAllByOrderByIdAsc(pageable);
    }

    public void save(NoticeLog noticeLog) {
        repository.save(noticeLog);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<NoticeLog> findAllByCurrentDate() {
        return repository.findAllByCurrentDate();
    }
}
