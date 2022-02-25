package com.example.dvdrental.api;

import com.example.dvdrental.repository.PaymentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PaymentsController.class)
public class PaymentsControllerTest {

    @MockBean
    PaymentRepository paymentRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getPayments() throws Exception {
        Double total = Double.valueOf("1234.56");
        Mockito.when(paymentRepository.totalRentalAmount()).thenReturn(total);
        MvcResult result = mockMvc.perform(get("/payments/total")).andExpect(status().isOk()).andReturn();
        assertEquals("1234.56", result.getResponse().getContentAsString());
    }

    @Test
    public void startDate() throws Exception {
        Date startDate = Date.from(Instant.now());
        Mockito.when(paymentRepository.startDate()).thenReturn(startDate);
        MvcResult result = mockMvc.perform(get("/payments/start")).andExpect(status().isOk()).andReturn();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String expectedDate = dateFormat.format(startDate);
        ObjectMapper mapper = new ObjectMapper();
        assertEquals(expectedDate, mapper.readTree(result.getResponse().getContentAsString()).get("start").asText());
    }


}
