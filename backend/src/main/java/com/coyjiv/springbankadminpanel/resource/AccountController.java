package com.coyjiv.springbankadminpanel.resource;

import com.coyjiv.springbankadminpanel.domain.Account.Account;
import com.coyjiv.springbankadminpanel.domain.Account.Currency;
import com.coyjiv.springbankadminpanel.domain.Customer.Customer;
import com.coyjiv.springbankadminpanel.service.AccountService;
import com.coyjiv.springbankadminpanel.service.CustomerService;
import com.coyjiv.springbankadminpanel.transfer.Account.AccountDTORequest;
import com.coyjiv.springbankadminpanel.transfer.Account.AccountDTOResponse;
import com.coyjiv.springbankadminpanel.transfer.Account.AccountRequestMapper;
import com.coyjiv.springbankadminpanel.transfer.Account.AccountResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final CustomerService customerService;
    private final AccountResponseMapper responseAccountMapper;
    private final AccountRequestMapper requestAccountMapper;

    //swagger annotate
    @Operation(summary = "get all accounts")
    @GetMapping("/")
    public List<AccountDTOResponse> findAll(){
        return accountService.findAll().stream().map(responseAccountMapper::convertToDto).toList();
    }

    @Operation(summary = "get an account by account number")
    @Parameter(name = "accountNumber", description = "account number")
    @GetMapping("/{accountNumber}")
    public AccountDTOResponse getOne(@PathVariable String accountNumber){
        return responseAccountMapper.convertToDto(accountService.getOne(accountNumber));
    }

    @Operation(summary = "create an account")
    @Parameter(name = "accountNumber", description = "account number")
    @Parameter(name = "currency", description = "currency")
    @Parameter(name = "balance", description = "balance")
    @Parameter(name = "customerId", description = "customer id")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody AccountDTORequest dto){
        try{
            Customer customer = customerService.getOne(dto.getCustomer().getId());
            Account account = accountService.createAccount(dto.getCurrency(), customer);
            accountService.save(account);
            return ResponseEntity.ok().body(responseAccountMapper.convertToDto(account));
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
    public ResponseEntity<?> edit(@RequestBody AccountDTORequest dto){
        try {
            Account account = accountService.getOne(dto.getAccountNumber());
            account.setCurrency(dto.getCurrency());
            account.setBalance(dto.getAccountBalance());
            account.setOwner(customerService.getOne(dto.getCustomer().getId()));
            accountService.save(account);
            return ResponseEntity.ok().body(responseAccountMapper.convertToDto(account));
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
    public List<AccountDTOResponse> findAllByCustomerId(@PathVariable String customerId){
        return customerService.getAccounts(Long.parseLong(customerId)).stream().map(responseAccountMapper::convertToDto).toList();
    }
    @Operation(summary = "top up an account")
    @Parameter(name = "accountNumber", description = "account number")
    @Parameter(name = "amount", description = "amount to top up")
    @PostMapping("/topUp")
    public ResponseEntity<?> topUp(@RequestBody AccountDTORequest dto){
        if(accountService.topUp(dto.getAccountNumber(), dto.getAccountBalance())){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "withdraw from an account")
    @Parameter(name = "accountNumber", description = "account number")
    @Parameter(name = "amount", description = "amount to withdraw")
    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody AccountDTORequest dto){
        if(accountService.withdraw(dto.getAccountNumber(), dto.getAccountBalance())){
            return ResponseEntity.ok().body(accountService.getOne(dto.getAccountNumber()));
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
