package com.example.monedita.service;

import com.example.monedita.entity.Expense;
import com.example.monedita.entity.enums.CategoryEnum;
import com.example.monedita.entity.enums.PaymentMethodEnum;

import java.time.LocalDate;
import java.util.List;

public interface IExpenseService {
    Expense createExpense(Expense expense);
    List<Expense> getAllExpenses();
    Expense getExpenseById(Long id);
    Expense updateExpense(Long id, Expense expense);
    void deleteExpenseById(Long id);
    List<Expense> getExpensesByCategory(CategoryEnum category);
    List<Expense> getExpensesByDate(LocalDate date);
    List<Expense> getExpensesByPaymentMethod(PaymentMethodEnum paymentMethod);
    //TODO: get expenses of month
}
