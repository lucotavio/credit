package br.com.luciano.credit.exception;

public class PaymentNotFoundException extends  RuntimeException{

    public PaymentNotFoundException(String message) {
        super(message);
    }
}
