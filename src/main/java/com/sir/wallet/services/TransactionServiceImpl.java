package com.sir.wallet.services;

import com.sir.wallet.model.Transaction;
import com.sir.wallet.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    TransactionRepository transRepository;
    @Override
    public Transaction createTransaction(Transaction transaction) {
        return this.transRepository.save(transaction);
    }

    @Override
    public Optional<Transaction> getTransactionById(Long id) {
        return this.transRepository.findById(id)
        ;
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        return this.transRepository.save(transaction)
        ;
    }

    @Override
    public void deleteTransaction(Transaction transaction) {
            this.transRepository.delete(transaction);
    }

    @Override
    public Iterable<Transaction> getAllTransactions() {

        return this.transRepository.findAll();
    }
}
