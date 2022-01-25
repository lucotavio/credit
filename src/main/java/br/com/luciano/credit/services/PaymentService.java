package br.com.luciano.credit.services;

import br.com.luciano.credit.domain.Payment;
import br.com.luciano.credit.domain.PaymentEvent;
import br.com.luciano.credit.domain.PaymentState;
import org.springframework.statemachine.StateMachine;

public interface PaymentService {

    Payment newPayment(Payment payment);

    StateMachine<PaymentState, PaymentEvent> preAuth(Long paymentId);

    StateMachine<PaymentState, PaymentEvent> authorizePayment(Long paymentId);

    StateMachine<PaymentState, PaymentEvent> declineAuth(Long paymentId);
}
