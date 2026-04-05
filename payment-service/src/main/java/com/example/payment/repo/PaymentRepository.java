package com.example.payment.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.payment.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
