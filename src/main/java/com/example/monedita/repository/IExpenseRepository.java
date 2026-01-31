package com.example.monedita.repository;

import com.example.monedita.model.Expense;
import com.example.monedita.model.enums.CategoryEnum;
import com.example.monedita.model.enums.PaymentMethodEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> getExpensesByCategory(CategoryEnum category);
    List<Expense> getExpensesByDate(LocalDate date);
    List<Expense> getExpensesByPaymentMethod(PaymentMethodEnum paymentMethod);
    //List<Expense> getExpensesBy
}
