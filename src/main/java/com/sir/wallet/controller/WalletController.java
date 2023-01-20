package com.sir.wallet.controller;


import com.sir.wallet.model.Wallet;
import com.sir.wallet.services.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/{id}")
    public Optional<Wallet> getWallet(@PathVariable long id) {
        return walletService.getWalletById(id);
    }

    @PutMapping("/{id}")
    public Wallet updateWallet(@PathVariable long id, @RequestBody Wallet wallet) {
        return walletService.updateWallet(id, wallet);
    }

    @PostMapping
    public Wallet createWallet(@RequestBody Wallet wallet) {
        return walletService.saveWallet(wallet);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteWallet(Wallet wallet )  {

        walletService.deleteWallet(wallet);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/wallets")
    public ResponseEntity<Iterable<Wallet>> getAllWallets() {
        return ResponseEntity.ok(walletService.getAllWallets());
    }
}
