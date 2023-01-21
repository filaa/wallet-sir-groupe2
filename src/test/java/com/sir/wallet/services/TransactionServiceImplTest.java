package com.sir.wallet.services;

import com.sir.wallet.model.Transaction;
import com.sir.wallet.model.Wallet;
import com.sir.wallet.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@SpringBootTest
class TransactionServiceImplTest {
    @InjectMocks
   TransactionServiceImpl transactionServiceImpl;

    @Mock
    TransactionRepository transactionRepository;

    @Test
    void createTransaction() {

        //Given

        Transaction transaction = new Transaction(new Wallet(1L,"Courant",100000),1000,"retrait");
        transaction.setId(1L);
        when(transactionRepository.save(any())).thenReturn(transaction);

        //When
        Transaction transResponse = transactionServiceImpl.createTransaction(transaction);

        //Then
        assertEquals(1L, transResponse.getId());
        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    void getTransactionById() {
        Transaction transaction= new Transaction(1L,new Wallet(1L,"Epargne",1000000),50000,"dépot");
        //Given

        when(transactionRepository.findById(any())).thenReturn(Optional.of(transaction));

        //When
       Optional<Transaction> transaction1 = transactionServiceImpl.getTransactionById(transaction.getId());

        //Then
        assertTrue(transaction1.isPresent());
        assertEquals(transaction1.get().getWallet(), transaction.getWallet());
        verify(transactionRepository, times(1)).findById(transaction.getId());
    }

    @Test
    void updateTransaction() {
        //Given
        Transaction transaction= new Transaction(1L,new Wallet(1L,"Epargne",1000000),50000,"dépot");
        when(transactionRepository.save(any())).thenReturn(transaction);
        transaction.setType("Retrait");
        transaction.setAmount(250000);
        //When
       Transaction transaction1 = transactionServiceImpl.updateTransaction(transaction);

        //Then
        assertEquals("Retrait", transaction1.getType());
        assertEquals(transaction.getAmount(), transaction1.getAmount());
        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    void deleteTransaction() {
        //Given
        Transaction transaction= new Transaction(1L,new Wallet(1L,"Epargne",1000000),50000,"dépot");
        doNothing().when(transactionRepository).delete(transaction);

        //When
        transactionServiceImpl.deleteTransaction(transaction);

        //Then
        verify(transactionRepository, times(1)).delete(transaction);
    }

    @Test
    void getAllTransactions() {
        //Given
        List<Transaction> transactions= new ArrayList<>();
        Wallet wallet = new Wallet(1L,"Compte5",500000);

        transactions.add(transactionRepository.save(new Transaction(wallet, 15000,"retrait")));
        transactions.add(transactionRepository.save(new Transaction(wallet, 50000,"depot")));
        transactions.add(transactionRepository.save(new Transaction(wallet,1000000, "depot")));
        transactions.add(transactionRepository.save(new Transaction(wallet,400000, "retrait")));
        when(transactionRepository.findAll()).thenReturn(transactions);

        //When

        Iterable<Transaction> transResponse = transactionServiceImpl.getAllTransactions();

        //Then
        assertEquals(transactions,transResponse);
        verify(transactionRepository, times(1)).findAll();
    }
}