package com.kkukielka.statemachine.config;

import com.kkukielka.statemachine.domain.PaymentEvent;
import com.kkukielka.statemachine.domain.PaymentState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StateMachineConfigTest {

    @Autowired
    StateMachineFactory<PaymentState, PaymentEvent> factory;

    @Test
    public void testNewStateMachine() {
        StateMachine<PaymentState, PaymentEvent> stateMachine = factory.getStateMachine(UUID.randomUUID());

        stateMachine.start();

        assertEquals(PaymentState.NEW, stateMachine.getState().getId());

        stateMachine.sendEvent(PaymentEvent.PRE_AUTHORIZE);

        assertEquals(PaymentState.NEW, stateMachine.getState().getId());

        stateMachine.sendEvent(PaymentEvent.PRE_AUTH_APPROVED);

        assertEquals(PaymentState.PRE_AUTH, stateMachine.getState().getId());
    }
}