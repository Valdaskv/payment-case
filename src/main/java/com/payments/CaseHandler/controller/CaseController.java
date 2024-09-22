package com.payments.CaseHandler.controller;

import com.payments.CaseHandler.dto.Case;
import com.payments.CaseHandler.dto.Payment;
import com.payments.CaseHandler.repository.CaseRepository;
import com.payments.CaseHandler.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
 CaseRepository repository;
    @Autowired
    PaymentRepository paymentRepository;
    @PostMapping(
            value = "/payment-cases",
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<String> addCase(@RequestBody Case aCase) {
//        PartnerList result = creditCalculatorService.getPartnerList();
        repository.save(aCase);
        System.out.println(aCase);
        return ResponseEntity.status(HttpStatus.OK).body(aCase.getId().toString());
    }



    @PutMapping(
            value = "/payment/add",
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<String> addPayment(@RequestBody Payment payment) {
//        PartnerList result = creditCalculatorService.getPartnerList();
        paymentRepository.save(payment);
        System.out.println(payment);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

//
//   @GetMapping(value = "/payment-cases")
//    public List<Case> getAllCases() {
//       System.out.println("getting all");
////      repository.findAll(Example.of(aCase));
//       return repository.findAll();
////
//   }

    @GetMapping(value = "/payment-cases")
    public List<Case> getFilteredCases(@RequestBody Case aCase, @PathVariable String action ) {
 List<Case> caseList =repository.findAll(Example.of(aCase));

//      repository.findAll(Example.of(aCase));
        return caseList;
//               repository.findAll();
    }

//
//    @GetMapping(value = "/payment/getAll")
//    public List<Payment> getAllPayments() {
//        System.out.println("getting all");
//        return paymentRepository.findAll();
//    }
}
