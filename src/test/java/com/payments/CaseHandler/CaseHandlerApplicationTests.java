package com.payments.CaseHandler;

import com.payments.CaseHandler.dto.Payment;
import com.payments.CaseHandler.dto.PaymentCase;
import com.payments.CaseHandler.service.ICaseService;
import com.payments.CaseHandler.util.CaseStatus;
import com.payments.CaseHandler.util.CaseType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CaseHandlerApplicationTests {
	@Autowired
	private ICaseService iCaseService;

	@Test
	void contextLoads() {

		System.out.println("context loaded");
	}


	public  void setUp() {
		//setup logic
		PaymentCase paymentCase = new PaymentCase(1L, new Payment(1L, new BigDecimal("3.25"), "EUR"), CaseType.NORMAL, CaseStatus.UNRESOLVED );
		iCaseService.save(paymentCase);
		System.out.println("before run");
	}


	@Test
	public void caseHandler_Success_return() {

		PaymentCase paymentCase = new PaymentCase(null, new Payment(null, new BigDecimal("3.25"), "EUR"), CaseType.NORMAL, CaseStatus.UNRESOLVED );
		iCaseService.save(paymentCase);
		paymentCase = new PaymentCase(null, new Payment(null, new BigDecimal("3.25"), "EUR"), CaseType.NORMAL, CaseStatus.UNRESOLVED );
		iCaseService.save(paymentCase);


		iCaseService.caseHandler();
		List<PaymentCase> all = iCaseService.getAll();
		PaymentCase paymentCase1 = all.get(0);
		assertEquals(1L, paymentCase1.getId());
		assertEquals(CaseType.NORMAL, paymentCase1.getType());
		assertEquals(CaseStatus.RESUBMIT, paymentCase1.getStatus());

		paymentCase1 = all.get(1);
		assertEquals(2L, paymentCase1.getId());
		assertEquals(CaseType.NORMAL, paymentCase1.getType());
		assertEquals(CaseStatus.RETURN, paymentCase1.getStatus());

	}

	@Test
	public void addPaymentCase_Success() {
		System.out.println("Test 1");
		PaymentCase paymentCase = new PaymentCase(1L, new Payment(1L, new BigDecimal("3.25"), "EUR"), CaseType.NORMAL, CaseStatus.UNRESOLVED );
		iCaseService.save(paymentCase);


		List<PaymentCase> all = iCaseService.getAll();
		PaymentCase paymentCase1 = all.get(0);
		assertEquals(1L, paymentCase1.getId());
		assertEquals(CaseType.NORMAL, paymentCase1.getType());
		assertEquals(CaseStatus.UNRESOLVED, paymentCase1.getStatus());
		assertEquals(1L, paymentCase1.getPayment().getId());
		assertEquals(new BigDecimal("3.25"), paymentCase1.getPayment().getAmount());
		assertEquals("EUR", paymentCase1.getPayment().getCurrency());
	}

	@Test
	public void caseHandler_Success() {
		PaymentCase paymentCase = new PaymentCase(1L, new Payment(1L, new BigDecimal("3.25"), "EUR"), CaseType.NORMAL, CaseStatus.UNRESOLVED );
		iCaseService.save(paymentCase);
		iCaseService.caseHandler();
		List<PaymentCase> all = iCaseService.getAll();
		PaymentCase paymentCase1 = all.get(0);
		assertEquals(1L, paymentCase1.getId());
		assertEquals(CaseType.NORMAL, paymentCase1.getType());
		assertEquals(CaseStatus.RETURN, paymentCase1.getStatus());
		assertEquals(1L, paymentCase1.getPayment().getId());
		assertEquals(new BigDecimal("3.25"), paymentCase1.getPayment().getAmount());
		assertEquals("EUR", paymentCase1.getPayment().getCurrency());
	}


}
