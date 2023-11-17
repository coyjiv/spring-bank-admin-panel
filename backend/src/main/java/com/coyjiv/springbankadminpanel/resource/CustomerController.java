package com.coyjiv.springbankadminpanel.resource;

import com.coyjiv.springbankadminpanel.domain.Account;
import com.coyjiv.springbankadminpanel.domain.Currency;
import com.coyjiv.springbankadminpanel.domain.Customer;
import com.coyjiv.springbankadminpanel.service.AccountService;
import com.coyjiv.springbankadminpanel.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
// enable all origins for now
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final AccountService accountService;

    public CustomerController(CustomerService customerService, AccountService accountService){
        this.customerService = customerService;
        this.accountService = accountService;
    }

    @Operation(summary = "get all customers")
    @GetMapping("/")
    public List<Customer> getCustomers(){
        return customerService.findAll();
    }

    @Operation(summary = "get a customer by id")
    @Parameter(name = "id", description = "customer id")
    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Long id){
        return customerService.getOne(id);
    }

    @Operation(summary = "create a customer")
    @Parameter(name = "name", description = "customer name")
    @Parameter(name = "email", description = "customer email")
    @Parameter(name = "age", description = "customer age")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Map<String, String> customerData){
        try{
        Customer customer = new Customer(customerData.get("name"), customerData.get("email"), Integer.parseInt(customerData.get("age")));
            customerService.save(customer);
            return ResponseEntity.ok().body(customer);
        } catch (Exception e){
            return ResponseEntity.badRequest().body("unable to create a user " + e);
        }
    }

    @Operation(summary = "edit a customer")
    @Parameter(name = "id", description = "customer id")
    @Parameter(name = "name", description = "customer name")
    @Parameter(name = "email", description = "customer email")
    @Parameter(name = "age", description = "customer age")
    @PutMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody Map<String, String> customerData){
        try {
            Customer customer = customerService.getOne(Long.parseLong(customerData.get("id")));
            if (customerService.edit(customerData)) {
                return ResponseEntity.ok().body(customer);
            } else {
                return ResponseEntity.badRequest().body("unable to edit a user");
            }
        } catch (Exception e){
            return ResponseEntity.badRequest().body("unable to edit a user " + e);
        }
    }

    @Operation(summary = "delete a customer")
    @Parameter(name = "id", description = "customer id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            Customer customer = customerService.getOne(id);
            customerService.delete(customer);
            return ResponseEntity.ok().body("deleted");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("unable to delete a user " + e);
        }
    }

    @Operation(summary = "Create an account for a customer")
    @Parameter(name = "customerId", description = "customer id")
    @Parameter(name = "currency", description = "currency")
    @PostMapping("/createAccount")
    public ResponseEntity<?> createAccount(@RequestBody Map<String, String> accountData){
        try {
            Customer customer = customerService.getOne(Long.parseLong(accountData.get("customerId")));
            Account account = accountService.createAccount(Currency.valueOf(accountData.get("currency")), customer);
            customer.addAccount(account);
            return ResponseEntity.ok().body(account);
        } catch (Exception e){
            return ResponseEntity.badRequest().body("unable to create an account " + e);
        }
    }

    @Operation(summary = "delete an account from a customer")
    @Parameter(name = "id", description = "account id")
    @DeleteMapping("/deleteAccount/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id){
        try {
            Account account = accountService.getOne(id);
            accountService.delete(account);
            return ResponseEntity.ok().body("deleted");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("unable to delete an account " + e);
        }
    }

}
