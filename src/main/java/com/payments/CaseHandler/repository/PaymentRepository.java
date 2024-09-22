package com.payments.CaseHandler.repository;

import com.payments.CaseHandler.dto.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
