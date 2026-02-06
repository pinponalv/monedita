package com.example.monedita.dto;

import com.example.monedita.model.enums.CategoryEnum;
import com.example.monedita.model.enums.PaymentMethodEnum;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseRequestDTO {

    @NotBlank(message = "The description is required")
    @Size(min = 5, max = 200, message = ("It must be more than 5 characters and less than 200"))
    private String description;

    @NotNull(message = "An amount is required")
    @Positive(message = "must be greater than zero")
    private BigDecimal amount;

    @NotNull(message = "It must have a category")
    private CategoryEnum category;

    @NotNull(message = "the date is required")
    @PastOrPresent(message = "the date cannot be future")
    private LocalDate date;

    @NotNull(message = "the payment method is required")
    private PaymentMethodEnum paymentMethod;

}
