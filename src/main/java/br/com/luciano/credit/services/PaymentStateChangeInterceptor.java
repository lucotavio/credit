package br.com.luciano.credit.services;

import br.com.luciano.credit.domain.Payment;
import br.com.luciano.credit.domain.PaymentEvent;
import br.com.luciano.credit.domain.PaymentState;
import br.com.luciano.credit.exception.PaymentNotFoundException;
import br.com.luciano.credit.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class PaymentStateChangeInterceptor extends StateMachineInterceptorAdapter<PaymentState, PaymentEvent> {

    private final PaymentRepository paymentRepository;

    @Override
    public void preStateChange(State<PaymentState, PaymentEvent> state, Message<PaymentEvent> message,
                               Transition<PaymentState, PaymentEvent> transition, StateMachine<PaymentState, PaymentEvent> stateMachine) {

        Optional.ofNullable(message).ifPresent(msg ->{
            Optional.ofNullable(Long.class.cast(msg.getHeaders().getOrDefault(PaymentServiceImpl.PAYMENT_ID_HEADER, -1)))
                    .ifPresent(paymentId ->{
                        Payment payment = paymentRepository.findById(paymentId).orElse(null);
                        payment.setState(state.getId());
                    });
        });
    }
}
