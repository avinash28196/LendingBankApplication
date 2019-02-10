package com.lendingclub.controller;


import com.lendingclub.exception.ResourceNotFoundException;
import com.lendingclub.model.LendingClub;
import com.lendingclub.repository.LendingClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class LendingClubController {

    @Autowired
    private LendingClubRepository lendingClubRepository;

    @GetMapping("/lendingClub")
    public Page<LendingClub> getAllDetails(Pageable pageable) {
        return lendingClubRepository.findAll(pageable);
    }


    @DeleteMapping("/lendingClub/{member_id}")
    public ResponseEntity<?> deletePost(@PathVariable Long member_id) {
        return lendingClubRepository.findById(member_id).map(details -> {
            lendingClubRepository.delete(details);
            System.out.println("Lending Club Memeber ID " + member_id + " Deleted");
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Lending Club Memeber ID " + member_id + " not found"));
    }

    @GetMapping("/lendingClub/{memberId}")
    public Page<LendingClub> getAllDetails(@PathVariable (value = "memberId") Long memberId, Pageable pageable) {
        return lendingClubRepository.findBymemberId(memberId, pageable);
    }

    @GetMapping("/lendingClub/Status/{verificationStatus}")
    public Page<LendingClub> getAllVerificationDetails(@PathVariable (value = "verificationStatus") String verificationStatus, Pageable pageable) {
        return lendingClubRepository.findByverificationStatus(verificationStatus, pageable);
    }




}
