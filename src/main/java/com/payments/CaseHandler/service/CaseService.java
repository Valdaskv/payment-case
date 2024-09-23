package com.payments.CaseHandler.service;

import com.payments.CaseHandler.dto.PaymentCase;
import com.payments.CaseHandler.repository.ICaseRepository;
import com.payments.CaseHandler.util.CaseStatus;
import com.payments.CaseHandler.util.CaseType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CaseService implements ICaseService{
    private final ICaseRepository repository;

    @Override
    public PaymentCase save(PaymentCase paymentCase) {
        return repository.save(paymentCase);
    }

    @Override
    public List<PaymentCase> getAll() {
        return repository.findAll();
    }

    @Override
    public List<PaymentCase> getNotProcessed(PaymentCase paymentCase) {
        return repository.findAll(Example.of(paymentCase));
    }

    @Override
    public List<PaymentCase> caseHandler() {
       PaymentCase paymentCase = new PaymentCase(null, null, null, CaseStatus.UNRESOLVED);
       List<PaymentCase> notProcessed = getNotProcessed(paymentCase);
       for (PaymentCase proc : notProcessed ) {
           // call remote case solver
           CaseType caseType = remoteCaseSolver(proc);
           if (caseType.equals(CaseType.RETURN)) {
               proc.setStatus(CaseStatus.RETURN);
           } else {
               proc.setStatus(CaseStatus.RESUBMIT);
           }
           repository.save(proc);
       }
       System.out.println(notProcessed);
       return notProcessed;
   }

    @Override
   public Long monitorUnresolved() {
       PaymentCase paymentCase = new PaymentCase(null, null, null, CaseStatus.UNRESOLVED);
        return (long) getNotProcessed(paymentCase).size();

   }

   public CaseType remoteCaseSolver(PaymentCase paymentCase) {
        if (paymentCase.getStatus().equals(CaseStatus.UNRESOLVED)) {
            if (paymentCase.getId() % 2 == 0) {
                return CaseType.RETURN;
            } else {
                return CaseType.NORMAL;
            }
        }
        return paymentCase.getType();
   }
}
