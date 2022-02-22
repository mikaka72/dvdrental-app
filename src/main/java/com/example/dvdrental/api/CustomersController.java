package com.example.dvdrental.api;

import com.example.dvdrental.entity.Customer;
import com.example.dvdrental.repository.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    private final CustomerRepository customerRepository;

    public CustomersController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        return customerRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping()
    public ResponseEntity createCustomer(@RequestBody Customer customer) throws URISyntaxException {
        Customer savedCustomer = customerRepository.save(customer);
        return ResponseEntity.created(new URI("/customers/" + savedCustomer.getCustomer_id())).body(savedCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer currentCustomer = customerRepository.findById(id).orElseThrow(RuntimeException::new);
        currentCustomer.setFirst_name(customer.getFirst_name());
        currentCustomer.setLast_name(customer.getLast_name());
        currentCustomer.setEmail(customer.getEmail());
    
        return ResponseEntity.ok(customerRepository.save(currentCustomer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
