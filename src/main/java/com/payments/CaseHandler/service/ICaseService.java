package com.payments.CaseHandler.service;

import com.payments.CaseHandler.dto.PaymentCase;

import java.util.List;

public interface ICaseService {
    PaymentCase save(PaymentCase paymentCase);

    List<PaymentCase> getAll();

    List<PaymentCase> getNotProcessed(PaymentCase paymentCase);

    List<PaymentCase>  caseHandler();
    Long monitorUnresolved();
}
