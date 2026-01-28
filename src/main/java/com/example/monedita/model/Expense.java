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

    @NotBlank(message = "La descripcion debe ser obligatoria")
    @Size(min = 5, max = 200, message = "Debe ser mayor a 5 caracteres y menor a 200")
    @Column(nullable = false)
    private String description;

    @NotNull(message = "debe haber una cantidad")
    @Column(nullable = false)
    private double amount;

    @NotNull(message = "debe tener una categoria")
    @Column(nullable = false)
    //en la base de datos no se muestra como un numero, si no como la categoria dicha
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    @NotNull(message = "la fecha de gasto es obligatoria")
    @PastOrPresent(message = "la fecha no puede ser futura")
    @Column(nullable = false)
    private LocalDate date;

    @NotNull(message = "la categoria del gasto es obligatoria")
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
