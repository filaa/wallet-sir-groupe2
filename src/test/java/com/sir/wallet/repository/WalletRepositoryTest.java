package com.sir.wallet.repository;

import com.sir.wallet.model.Wallet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WalletRepositoryTest {
    @Autowired
    WalletRepository walletRepository;

    @Test
    void save() {
        //Given

        final Long testId = 1L;
        Wallet wallet = new Wallet(testId, "My Wallet", 100);

        //When
        Wallet responseWallet = walletRepository.save(wallet);

        //Then
        assertNotNull(responseWallet);
        assertEquals(responseWallet.getName(), wallet.getName());

    }
}
