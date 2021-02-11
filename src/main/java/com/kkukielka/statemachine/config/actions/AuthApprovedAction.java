package com.kkukielka.statemachine.config.actions;

import com.kkukielka.statemachine.domain.PaymentEvent;
import com.kkukielka.statemachine.domain.PaymentState;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@Component
public class AuthApprovedAction implements Action<PaymentState, PaymentEvent> {
    @Override
    public void execute(StateContext<PaymentState, PaymentEvent> stateContext) {
        System.out.println("Sending notification - AUTH APPROVED");
    }
}
