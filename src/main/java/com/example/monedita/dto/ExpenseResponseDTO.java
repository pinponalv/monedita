package com.example.monedita.dto;

import com.example.monedita.model.enums.CategoryEnum;
import com.example.monedita.model.enums.PaymentMethodEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ExpenseResponseDTO {
    //los datos que mostrarare, si no quiero mostrar algo lo quito
    private Long id;
    private String description;
    private BigDecimal amount;
    private CategoryEnum category;
    private LocalDate date;
    private PaymentMethodEnum paymentMethod;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
