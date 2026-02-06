package com.example.monedita.model;

import com.example.monedita.model.enums.CategoryEnum;
import com.example.monedita.model.enums.PaymentMethodEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "expenses")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor

public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    //bigdecimal es para trabajar con dinero
    //la columna nunca puede ser null
    @Column(nullable = false)
    private BigDecimal amount;


    @Column(nullable = false)
    //en la base de datos no se muestra como un numero, si no como la categoria dicha
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    @Column(nullable = false)
    private LocalDate date;


    @Column(nullable = false, name = "payment_method")
    //en la base de datos no se muestra como un numero, si no como metodo de pago
    @Enumerated(EnumType.STRING)
    private PaymentMethodEnum paymentMethod;

    //cuando se crea el registro
    //no se puede actualizar
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    //la ultima actualizacion
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
