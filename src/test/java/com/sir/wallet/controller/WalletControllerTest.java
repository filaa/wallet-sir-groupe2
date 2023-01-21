package com.sir.wallet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sir.wallet.model.Wallet;
import com.sir.wallet.services.WalletService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class WalletControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    WalletService walletService;
    ObjectMapper objectMapper= new ObjectMapper();
    @Test
    void getWallet() throws Exception {
        Wallet wallet= new Wallet(1L,"Compte courant",1000);
        when(walletService.getWalletById(1L)).thenReturn(Optional.of(wallet));
        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/wallet/wallets/1")
                .contentType(MediaType.APPLICATION_JSON);
        //When
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        // Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());


        assertTrue(response.getContentAsString().contains("{\"id\":1,\"name\":\"Compte courant\",\"balance\":1000}"));


    }

    @Test
    void updateWallet() throws Exception{
        //Given
         Wallet wallet=  new Wallet(1L,"Compte courant",1000);
        when(walletService.updateWallet(any(),any())).thenReturn(wallet);
        RequestBuilder request = MockMvcRequestBuilders
                .put("/api/wallet/wallets/1")
                .content(objectMapper.writeValueAsString(wallet))
                .contentType(MediaType.APPLICATION_JSON); // "application/json"


        //When
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        // Then
        assertTrue(response.getContentAsString().contains("1000"));

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void saveWallet() throws Exception {
        //Given
        Wallet wallet= new Wallet(1L,"Compte courant",1000);
        when(walletService.saveWallet(any())).thenReturn(wallet);
        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/wallet/wallets")
                .content(objectMapper.writeValueAsString(wallet))
                .contentType(MediaType.APPLICATION_JSON); // "application/json"


        //When
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        // Then
        assertTrue(response.getContentAsString().contains("Compte courant"));

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void deleteWallet() throws Exception {
        //Given
        Wallet wallet =new Wallet(1L,"Compte courant",1000);

        doNothing().when(walletService).deleteWallet(wallet);
        RequestBuilder request = MockMvcRequestBuilders
                .delete("/api/wallet/wallets/1")
                .content(objectMapper.writeValueAsString(wallet))
                .contentType(MediaType.APPLICATION_JSON); // "application/json"


        //When
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        // Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void getAllWallets() throws Exception {
        //Given
        List<Wallet> wallets= new ArrayList<>();
        wallets.add(new Wallet(1L,"Compte5",500000));
        when(walletService.getAllWallets()).thenReturn(wallets);
        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/wallet/wallets")
                .contentType(MediaType.APPLICATION_JSON); // "application/json"


        //When
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        // Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());


        assertTrue(response.getContentAsString().contains(objectMapper.writeValueAsString(wallets)));
    }
}