package com.kkukielka.statemachine.config.actions;

import com.kkukielka.statemachine.domain.PaymentEvent;
import com.kkukielka.statemachine.domain.PaymentState;
import com.kkukielka.statemachine.services.PaymentServiceImpl;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.Random;

import static com.kkukielka.statemachine.domain.PaymentEvent.PRE_AUTH_APPROVED;
import static com.kkukielka.statemachine.domain.PaymentEvent.PRE_AUTH_DECLINED;

@Component
public class PreAuthAction implements Action<PaymentState, PaymentEvent> {
    @Override
    public void execute(StateContext<PaymentState, PaymentEvent> stateContext) {
        System.out.println("PreAuth was called");

        if (new Random().nextInt(10) < 8) {
            System.out.println("Approved!!");
            stateContext.getStateMachine().sendEvent(MessageBuilder
                    .withPayload(PRE_AUTH_APPROVED).setHeader(PaymentServiceImpl.PAYMENT_ID_HEADER,
                            stateContext.getMessageHeader(PaymentServiceImpl.PAYMENT_ID_HEADER))
                    .build());
        } else {
            System.out.println("Declined!!");
            stateContext.getStateMachine().sendEvent(MessageBuilder
                    .withPayload(PRE_AUTH_DECLINED).setHeader(PaymentServiceImpl.PAYMENT_ID_HEADER,
                            stateContext.getMessageHeader(PaymentServiceImpl.PAYMENT_ID_HEADER))
                    .build());
        }
    }
}
