package com.coyjiv.springbankadminpanel.resource;

import com.coyjiv.springbankadminpanel.domain.Account.Account;
import com.coyjiv.springbankadminpanel.domain.Account.Currency;
import com.coyjiv.springbankadminpanel.domain.Customer.Customer;
import com.coyjiv.springbankadminpanel.service.AccountService;
import com.coyjiv.springbankadminpanel.service.CustomerService;
import com.coyjiv.springbankadminpanel.transfer.Account.AccountDTORequest;
import com.coyjiv.springbankadminpanel.transfer.Account.AccountRequestMapper;
import com.coyjiv.springbankadminpanel.transfer.Account.AccountResponseMapper;
import com.coyjiv.springbankadminpanel.transfer.Customer.CustomerDTORequest;
import com.coyjiv.springbankadminpanel.transfer.Customer.CustomerDTOResponse;
import com.coyjiv.springbankadminpanel.transfer.Customer.CustomerRequestMapper;
import com.coyjiv.springbankadminpanel.transfer.Customer.CustomerResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final AccountService accountService;
    private final CustomerRequestMapper customerRequestMapper;
    private final CustomerResponseMapper customerResponseMapper;
    private final AccountResponseMapper accountResponseMapper;

    @Operation(summary = "get all customers")
    @GetMapping("/")
    public List<CustomerDTOResponse> getCustomers(){
        return customerService.findAll().stream().map(customerResponseMapper::convertToDto).toList();
    }

    @Operation(summary = "get a customer by id")
    @Parameter(name = "id", description = "customer id")
    @GetMapping("/{id}")
    public CustomerDTOResponse getCustomer(@PathVariable Long id){
        return customerResponseMapper.convertToDto(customerService.getOne(id));
    }

    @Operation(summary = "create a customer")
    @Parameter(name = "name", description = "customer name")
    @Parameter(name = "email", description = "customer email")
    @Parameter(name = "age", description = "customer age")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CustomerDTORequest dto){
        try{
        Customer customer = new Customer(dto.getName(), dto.getEmail(), dto.getAge(), dto.getPhone(), dto.getPassword());
            customerService.save(customer);
            return ResponseEntity.ok().body(customerResponseMapper.convertToDto(customer));
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
    public ResponseEntity<?> edit(@RequestBody CustomerDTORequest dto){
        try {
            Customer customer = customerService.getOne(dto.getId());
            if (customerService.edit(customerRequestMapper.convertToEntity(dto))) {
                return ResponseEntity.ok().body(customerRequestMapper.convertToDto(customer));
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
            customerService.deleteById(id);
            return ResponseEntity.ok().body("deleted");
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("unable to delete a user " + e);
        }
    }

    @Operation(summary = "Create an account for a customer")
    @Parameter(name = "customerId", description = "customer id")
    @Parameter(name = "currency", description = "currency")
    @PostMapping("/createAccount")
    public ResponseEntity<?> createAccount(@RequestBody AccountDTORequest dto){
        try {
            Customer customer = customerService.getOne(dto.getCustomer().getId());
            Account account = accountService.createAccount(dto.getCurrency(), customer);
            customer.addAccount(account);
            return ResponseEntity.ok().body(accountResponseMapper.convertToDto(account));
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
