package com.sir.wallet.cucumber.steps;


import com.sir.wallet.controller.WalletController;
import com.sir.wallet.model.Wallet;
import com.sir.wallet.repository.WalletRepository;
import com.sir.wallet.services.WalletService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = TestConfiguration.class)
public class WalletStepsSir {

private Wallet wallet;

private ResponseEntity<Wallet> response;
private ResponseEntity<List<Wallet>> listresponse;

private List<Wallet> wallets;

@Autowired
    private WalletRepository walletRepository;
@Autowired
    private WalletController walletController;

    @Given("a wallet with name {string} and balance {long}")
    public void createWallet(String name, long balance) {

        wallet = new Wallet(1,"test", 2000);

    }

    @When("I POST the wallet to the {string} endpoint")
    public void postWallet(String endpoint) {

      response=walletController.saveWallet(wallet);

    }

    @Then("the response status should be {int}")
    public void checkResponseStatus(int status) {

        assertEquals(status, response.getStatusCode().value());
    }

    @And("the response body should contain the wallet")
    public void checkResponseBody() {
        Wallet personResponse = response.getBody();
        assertNotNull(personResponse);

    }


}
