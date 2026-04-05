package com.example.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.payment.entity.Payment;
import com.example.payment.repo.PaymentRepository;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentRepository repo;

    // ✅ Create Payment
    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        return repo.save(payment);
    }

    // ✅ Get All Payments
    @GetMapping
    public List<Payment> getAllPayments() {
        return repo.findAll();
    }

    // ✅ Get Payment by ID
    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable int id) {
        return repo.findById(id).orElse(null);
    }
}
