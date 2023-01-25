package com.sir.wallet.cucumber.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sir.wallet.controller.TransactionController;
import com.sir.wallet.controller.WalletController;
import com.sir.wallet.model.Transaction;
import com.sir.wallet.model.Wallet;
import com.sir.wallet.repository.TransactionRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class TransactionStepdefs {
    Wallet wallet = new Wallet();
    Wallet walletOther = new Wallet();


    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }



    @Autowired
    TransactionRepository transactionRepository;


    @Autowired
    TransactionController transactionController;

    @Autowired
    WalletController walletController;
    MockHttpServletResponse response;
   
    @Given("a wallet with ID{int} and name{string} and balance{int} in stepdef")
    public void aWalletWithIdAndNameAndBalance(long id, String name, long balance) {
        wallet = new Wallet(id,name,balance);
        this.walletController.saveWallet(wallet);
    }
    @And( "And a wallet with ID {int} and name {string} and balance {int}")
    public void andAWalletWithIdAndNameAndBalance(long id, String name, long balance){
        walletOther= new Wallet(id,name,balance);
        this.walletController.saveWallet(walletOther);

    }
    @When("I POST a transaction with wallet ID {int} and amount {int} and type {string} to the {string} endpoint")
    public void IPOSTATransactionWithWalletIDAndAmountAndTypeToTheEndpoint(int id, int amount, String type, String endpoint) throws Exception {

        Transaction transaction = new Transaction(id,amount,type);


        ObjectMapper objectMapper = new ObjectMapper();
        byte[] requestBody = objectMapper.writeValueAsBytes(transaction);
        RequestBuilder request = MockMvcRequestBuilders
                .get("http://localhost:8080/transaction"+endpoint)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON); // "application/json"


        //When
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        response = mvcResult.getResponse();
    }

    @And("the wallet with ID {int} should have a balance of {int} ")
    public void theWalletWithIDShouldHaveABalanceOf(int id, int balance) {
       Optional<Wallet> wallet =walletController.getWallet(id);
       wallet.get().setBalance(balance);

    }


    @Then("the response status should be {int} ")
    public void theResponseStatusShouldBe(int status){
        assertEquals(status, response.getStatus());
    }

}
