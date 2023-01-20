package com.sir.wallet.controller;

import com.sir.wallet.model.Transaction;
import com.sir.wallet.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTransaction( Transaction transaction )  {

        transactionService.deleteTransaction(transaction);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(Transaction transaction) {
        return ResponseEntity.ok(transactionService.updateTransaction(transaction));
    }

    @GetMapping("/transactions")
    public ResponseEntity<Iterable<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Transaction>> getTransactionById( @PathVariable("id") Long id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }



}
