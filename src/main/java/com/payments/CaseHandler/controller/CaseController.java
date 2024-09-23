package com.payments.CaseHandler.controller;

import com.payments.CaseHandler.dto.PaymentCase;
import com.payments.CaseHandler.service.ICaseService;
import com.payments.CaseHandler.util.CaseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CaseController {
    @Autowired
    ICaseService service;
    @PostMapping(
            value = "/payment-cases",
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<PaymentCase> addPaymentCase(@RequestBody PaymentCase paymentCase) {
        PaymentCase save = service.save(paymentCase);

        System.out.println(paymentCase);
        return new ResponseEntity<>(save, HttpStatus.OK);
    }

    @GetMapping(value = "/payment-cases")
    public ResponseEntity<List<PaymentCase>>  getFilteredPaymentCases(@RequestParam(value = "action", required = false) CaseStatus action) {
        List<PaymentCase> paymentCases;
        if (action != null) {
             paymentCases = service.getNotProcessed(new PaymentCase(null, null, null, action));
        } else  {
            paymentCases = service.getAll();
        }
        return new ResponseEntity<>(paymentCases, HttpStatus.OK);
    }


    @GetMapping(value = "/case-handler")
    public ResponseEntity<List<PaymentCase>> processPayment() {
        List<PaymentCase> paymentCases = service.caseHandler();
        return new ResponseEntity<>(paymentCases, HttpStatus.OK);
    }


    @GetMapping(value = "/monitor")
    public ResponseEntity<String> monitor() {
        Long unresolvedCount = service.monitorUnresolved();
        return new ResponseEntity<>("Count of unresolved cases are :" + unresolvedCount, HttpStatus.OK);
    }

}
