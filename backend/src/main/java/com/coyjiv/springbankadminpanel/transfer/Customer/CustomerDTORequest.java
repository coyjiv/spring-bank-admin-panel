package com.coyjiv.springbankadminpanel.transfer.Customer;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTORequest {
    // optional id
    private Long id;
    @NotBlank
    @Size(min = 2, message = "Name must be longer then 2 words")
    private String name;
    @Email(message = "Not valid email")
    private String email;
    @Min(value = 18, message = "Age must be grater then 18")
    private Integer age;
    @NotBlank
    private String password;
    @NotBlank
    @Pattern(regexp = "(\\+380|380)[0-9]{9}", message = "Not valid phone number")
    private String phone;
}
