package com.bridgelabz.atmsystem.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Purpose : Class is created for atm-details
 *
 * @author : DAMINI CHANDRAKAR
 * @since : 03-12-2021
 */
@Data
public class AtmDto {

    @NotNull
    @Pattern(regexp = "^[0-9]{4}[ ][0-9]{4}[ ][0-9]{4}[ ][0-9]{4}",
            message = "Card Number Should have only 16 digit")
    private String cardNumber;

    @NotNull
    @Pattern(regexp = "^[A-Z][a-z]{2,}",
            message = "Name should have at least 3 characters && start with capital latter ")
    private String cardName;

    @Pattern(regexp = "^[0-9]{3}",
            message = "CVV Should have only 3 digit")
    private String cvv;
}
