package com.kkukielka.statemachine.services;

import com.kkukielka.statemachine.domain.Payment;
import com.kkukielka.statemachine.domain.PaymentEvent;
import com.kkukielka.statemachine.domain.PaymentState;
import com.kkukielka.statemachine.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentServiceImplTest {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentRepository paymentRepository;

    private Payment payment;

    @BeforeEach
    public void setUp() {
        payment = Payment.builder().amount(new BigDecimal("10.29")).build();
    }

    @Transactional
    @Test
    void preAuth() {
        Payment savedPayment = paymentService.newPayment(payment);

        StateMachine<PaymentState, PaymentEvent> stateMachine = paymentService.preAuth(savedPayment.getId());

        Payment preAuthPayment = paymentRepository.getOne(savedPayment.getId());

        System.out.println(preAuthPayment);

        PaymentState state = stateMachine.getState().getId();
        boolean preAuthOrDeclined = state.equals(PaymentState.PRE_AUTH) || state.equals(PaymentState.PRE_AUTH_ERROR);
        assertTrue(preAuthOrDeclined);
        assertEquals(savedPayment.getAmount(), preAuthPayment.getAmount());
        assertTrue(savedPayment.getState().equals(PaymentState.PRE_AUTH)
                || savedPayment.getState().equals(PaymentState.PRE_AUTH_ERROR));
    }
}