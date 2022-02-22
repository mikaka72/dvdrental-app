package com.example.dvdrental.api;

import com.example.dvdrental.entity.Customer;
import com.example.dvdrental.repository.CustomerRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomersController.class)
public class CustomersControllerTest {

    @MockBean
    CustomerRepository customerRepository;

    @Autowired
    MockMvc mockMvc;

    private final Customer customer = Customer.builder().customer_id(1L).store_id(1L).active(1).activebool(true).create_date(new Date()).first_name("firstName").last_name("lastName").email("firstname.lastname@gmail.com").address_id(1L).last_update(new Date()).build();
    private final String customerJson = "{\"customer_id\":null,\"store_id\":1,\"first_name\":\"firstName\",\"last_name\":\"lastName\",\"email\":\"firstname.lastname@gmail.com\",\"address_id\":1,\"activebool\":true,\"create_date\":null,\"last_update\":null,\"active\":1}";
    private final String updateCustomerJson = "{\"customer_id\":null,\"store_id\":5,\"first_name\":\"John\",\"last_name\":\"Doe\",\"email\":\"John.Doe@gmail.com\",\"address_id\":5,\"activebool\":true,\"create_date\":null,\"last_update\":null,\"active\":1}";

    @Test
    public void getCustomers() throws Exception {

        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        Mockito.when(customerRepository.findAll()).thenReturn(customers);
        MvcResult result = mockMvc.perform(get("/customers")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1))).andExpect(jsonPath("$[0].customer_id", Matchers.is(1))).andReturn();
        List<Customer> responseCustomers = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<List<Customer>>() {
        });
        assertEquals(customer, responseCustomers.get(0));

    }

    @Test
    public void getCustomerWithId() throws Exception {
        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        MvcResult result = mockMvc.perform(get("/customers/{id}", 1L)).andExpect(status().isOk()).andExpect(jsonPath("customer_id", Matchers.is(1))).andReturn();
        assertEquals(customer, new ObjectMapper().readValue(result.getResponse().getContentAsString(), Customer.class));
    }

    @Test
    public void createCustomer() throws Exception {
        Customer toBeSavedCustomer = new ObjectMapper().readValue(customerJson, Customer.class);
        Mockito.when(customerRepository.save(toBeSavedCustomer)).thenReturn(customer);
        MvcResult result = mockMvc.perform(post("/customers").content(customerJson).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated()).andExpect(jsonPath("customer_id", Matchers.is(1))).andReturn();
        assertEquals(customer, new ObjectMapper().readValue(result.getResponse().getContentAsString(), Customer.class));
    }

    @Test
    public void deleteCustomer() throws Exception {
        doNothing().when(customerRepository).deleteById(any(Long.class));
        mockMvc.perform(delete("/customers/{id}", 1L)).andExpect(status().isOk());
    }

    @Test
    public void updateCustomer() throws Exception {
        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        Customer customerUpdated = customer;
        customerUpdated.setFirst_name("John");
        customerUpdated.setLast_name("Doe");
        customerUpdated.setEmail("John.Doe@gmail.com\"");
        Mockito.when(customerRepository.save(customerUpdated)).thenReturn(customerUpdated);
        MvcResult result = mockMvc.perform(put("/customers/{id}", 1L).content(updateCustomerJson).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
        Customer updatedCustomer = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Customer.class);
        // firstname, lastname and email are the only fields that can be updated via this api so check ....
        assertEquals("John", updatedCustomer.getFirst_name());
        assertEquals("Doe", updatedCustomer.getLast_name());
        assertEquals("John.Doe@gmail.com", updatedCustomer.getEmail());
        assertEquals(customer.getCustomer_id(), updatedCustomer.getCustomer_id());
    }

}
