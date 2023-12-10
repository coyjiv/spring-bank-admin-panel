package com.coyjiv.springbankadminpanel.resource;

import com.coyjiv.springbankadminpanel.domain.Account;
import com.coyjiv.springbankadminpanel.domain.Currency;
import com.coyjiv.springbankadminpanel.domain.Customer;
import com.coyjiv.springbankadminpanel.service.AccountService;
import com.coyjiv.springbankadminpanel.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
//@CrossOrigin("http://localhost:3000")
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;
    private final CustomerService customerService;

    public AccountController(AccountService accountService, CustomerService customerService){
        this.accountService = accountService;
        this.customerService = customerService;
    }

    //swagger annotate
    @Operation(summary = "get all accounts")
    @GetMapping("/")
    public List<Account> findAll(){
        return accountService.findAll();
    }

    @Operation(summary = "get an account by account number")
    @Parameter(name = "accountNumber", description = "account number")
    @GetMapping("/{accountNumber}")
    public Account getOne(@PathVariable String accountNumber){
        return accountService.getOne(accountNumber);
    }

    @Operation(summary = "create an account")
    @Parameter(name = "accountNumber", description = "account number")
    @Parameter(name = "currency", description = "currency")
    @Parameter(name = "balance", description = "balance")
    @Parameter(name = "customerId", description = "customer id")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Map<String, String> json){
        try{
            Customer customer = customerService.getOne(Long.parseLong(json.get("customerId")));
            Account account = accountService.createAccount(Currency.valueOf(json.get("currency")), customer);
            accountService.save(account);
            return ResponseEntity.ok().body(account);
        } catch (Exception e){
            return ResponseEntity.badRequest().body("unable to create an account " + e);
        }
    }

    @Operation(summary = "edit an account")
    @Parameter(name = "accountNumber", description = "account number")
    @Parameter(name = "currency", description = "currency")
    @Parameter(name = "balance", description = "balance")
    @Parameter(name = "customerId", description = "customer id")
    @PutMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody Map<String, String> json){
        try {
            Account account = accountService.getOne(json.get("accountNumber"));
            account.setCurrency(Currency.valueOf(json.get("currency")));
            account.setBalance(Double.parseDouble(json.get("balance")));
            account.setOwner(customerService.getOne(Long.parseLong(json.get("customerId"))));
            accountService.save(account);
            return ResponseEntity.ok().body(account);
        } catch (Exception e){
            return ResponseEntity.badRequest().body("unable to edit an account " + e);
        }
    }

    @Operation(summary = "delete an account")
    @Parameter(name = "accountNumber", description = "account number")
    @DeleteMapping("/delete/{accountNumber}")
    public ResponseEntity<?> delete(@PathVariable String accountNumber){
        try {
            accountService.deleteById(Long.parseLong(accountNumber));
            return ResponseEntity.ok().body("deleted");
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("unable to delete an account " + e);
        }
    }

    @Operation(summary = "get all accounts of a customer")
    @Parameter(name = "customerId", description = "customer id")
    @GetMapping("/customer/{customerId}")
    public List<Account> findAllByCustomerId(@PathVariable String customerId){
        return customerService.getAccounts(Long.parseLong(customerId));
    }

    @Operation(summary = "top up an account")
    @Parameter(name = "accountNumber", description = "account number")
    @Parameter(name = "amount", description = "amount to top up")
    @PostMapping("/topUp")
    public ResponseEntity<?> topUp(@RequestBody Map<String, String> json){
        if(accountService.topUp(json.get("accountNumber"), Double.parseDouble(json.get("amount")))){
            return ResponseEntity.ok().body(accountService.getOne(json.get("accountNumber")));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "withdraw from an account")
    @Parameter(name = "accountNumber", description = "account number")
    @Parameter(name = "amount", description = "amount to withdraw")
    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody Map<String, String> json){
        if(accountService.withdraw(json.get("accountNumber"), Double.parseDouble(json.get("amount")))){
            return ResponseEntity.ok().body(accountService.getOne(json.get("accountNumber")));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "transfer from an account")
    @Parameter(name = "fromAccountNumber", description = "account number to transfer from")
    @Parameter(name = "toAccountNumber", description = "account number to transfer to")
    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody Map<String, String> json){
        if(accountService.transfer(json.get("fromAccountNumber"), json.get("toAccountNumber"), Double.parseDouble(json.get("amount")))){
            return ResponseEntity.ok().body(accountService.getOne(json.get("fromAccountNumber")));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
