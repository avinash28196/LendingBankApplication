package com.lendingclub.repository;

import com.lendingclub.model.LendingClub;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface LendingClubRepository extends JpaRepository<LendingClub, Long>{

    Page<LendingClub> findBymemberId(Long memberId, Pageable pageable);
    Page<LendingClub> findByverificationStatus(String verificationStatus, Pageable pageable);
}
