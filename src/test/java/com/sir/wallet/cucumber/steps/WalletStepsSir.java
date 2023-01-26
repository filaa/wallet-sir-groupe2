package com.sir.wallet.cucumber.steps;


import com.sir.wallet.controller.WalletController;
import com.sir.wallet.model.Wallet;
import com.sir.wallet.repository.WalletRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = TestConfiguration.class)
public class WalletStepsSir {

    private final RestTemplate restTemplate;
    private Wallet wallet;
private ResponseEntity<Wallet> response;
    private ResponseEntity<Wallet> response1;

private ResponseEntity<List<Wallet>> listresponse;

private Wallet wallets;

@Autowired
    private WalletRepository walletRepository;

    @Autowired
    public WalletStepsSir(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
@Autowired
    private WalletController walletController;
    @Given("a wallet with name {string} and balance {long}")
    public void createWallet(String name, long balance) {

        wallet = new Wallet(name, balance);

    }
    @When("I POST the wallet to the {string} endpoint")
    public void postWallet(String endpoint) {

      response=walletController.saveWallet(wallet);

    }
    @Then("the response status should be {long}")
    public void checkResponseStatus(long status) {

        assertEquals(status, response.getStatusCode().value());

    }
    @And("the response body should contain the wallet")
    public void checkResponseBody() {
        Wallet personResponse = response.getBody();
        assertNotNull(personResponse);


    }

    @Given("a wallet with ID {long} and name {string} and balance {long}")
    public void createWallet(long id, String name, long balance) {
        wallet = new Wallet(id, name, balance);
    }

    @When("I GET the wallet from the {string} endpoint")
    public void getWallet(String endpoint) {
        String url = "http://localhost:8080/api/wallet/"+endpoint;
        response = restTemplate.getForEntity(url, Wallet.class);
    }


    @When("I PUT the wallet to the {string} endpoint with name {string} and balance {long}")
    public void updateWallet(String endpoint, String name, long balance) {
        wallet.setBalance(balance);
        wallet.setName(name);
        String url = "http://localhost:8080/api/wallet/"+endpoint;
        response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(wallet), Wallet.class);
    }
    @And("the response body should contain the updated wallet")
    public void checkUpdateResponseBody() {
        Wallet updatedWallet = response.getBody();

            assertEquals(wallet.getName(), updatedWallet.getName());
            assertEquals(wallet.getBalance(), updatedWallet.getBalance());


    }

}
