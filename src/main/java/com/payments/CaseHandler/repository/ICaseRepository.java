package com.payments.CaseHandler.repository;

import com.payments.CaseHandler.dto.PaymentCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICaseRepository extends JpaRepository<PaymentCase, Long> {
}
