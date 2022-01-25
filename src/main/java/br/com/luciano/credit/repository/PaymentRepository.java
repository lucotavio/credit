package br.com.luciano.credit.repository;

import br.com.luciano.credit.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
