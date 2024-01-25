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
import java.util.Optional;

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
    @GetMapping("/{page}/{size}")
    public List<CustomerDTOResponse> getCustomers(@PathVariable int page, @PathVariable int size){
        return customerService.findAll(page, size).stream().map(customerResponseMapper::convertToDto).toList();
    }

    @Operation(summary = "get pagination info")
    @GetMapping("/pagination/{page}/{size}")
    public Map<String, Integer> getPaginationInfo(@PathVariable int page, @PathVariable int size){
        return customerService.getPaginationInfo(page, size);
    }
    @Operation(summary = "get a customer by id")
    @Parameter(name = "id", description = "customer id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable Long id){
        Optional<Customer> customer = customerService.getOne(id);
         if(customer.isPresent()){
             return ResponseEntity.ok(customerResponseMapper.convertToDto(customer.get()));
         } else{
                return ResponseEntity.badRequest().body("unable to find a user");
         }
    }

    @Operation(summary = "create a customer")
    @PostMapping("/")
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
    @PutMapping("/")
    public ResponseEntity<?> edit(@RequestBody CustomerDTORequest dto){
        try {
            Optional<Customer> customer = customerService.getOne(dto.getId());
            System.out.println(dto);
            if (customer.isPresent()) {
                customerService.edit(customerRequestMapper.convertToEntity(dto));
                return ResponseEntity.ok().body(customerRequestMapper.convertToDto(customer.get()));
            } else {
                return ResponseEntity.badRequest().body("unable to edit a user, cannot find a user");
            }
        } catch (Exception e){
            return ResponseEntity.badRequest().body("ERROR unable to edit a user " + e);
        }
    }

    @Operation(summary = "delete a customer")
    @Parameter(name = "id", description = "customer id")
    @DeleteMapping("/{id}")
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
    @PostMapping("/createAccount/{id}/{currency}")
    public ResponseEntity<?> createAccount(@PathVariable Long id, @PathVariable Currency currency){
        try {
            Optional<Customer> customer = customerService.getOne(id);
            Account account = accountService.createAccount(currency, customer.get());
            return ResponseEntity.ok().body(accountResponseMapper.convertToDto(account));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("unable to create an account " + e);
        }
    }

}
