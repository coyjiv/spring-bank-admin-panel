package com.coyjiv.springbankadminpanel.resource;

import com.coyjiv.springbankadminpanel.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
//@CrossOrigin("http://localhost:3000")
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    //swagger annotate

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
