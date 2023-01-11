package io.github.guimatech.domdaterra.billingrobot.infra.out.db.repository;

import io.github.guimatech.domdaterra.billingrobot.domain.Billing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillingRepository extends PagingAndSortingRepository<Billing, Long> {

    @Query(value = "SELECT b.* FROM billing b" +
            " WHERE b.expiration = current_date" +
            " OR b.expiration = CURRENT_DATE + INTERVAL '20 day'" +
            " OR b.expiration = CURRENT_DATE + INTERVAL '40 day'" +
            " OR b.expiration = CURRENT_DATE + INTERVAL '60 day'", nativeQuery = true)
    List<Billing> findByCurrentDate();

    Page<Billing> findAllByOrderByIdAsc(Pageable pageable);
}
