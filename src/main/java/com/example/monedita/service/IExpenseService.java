package com.example.monedita.service;

import com.example.monedita.model.Expense;
import com.example.monedita.model.enums.CategoryEnum;

import java.util.List;
import java.util.Optional;

public interface IExpenseService {
    Expense createExpense(Expense expense);
    List<Expense> getAllExpenses();
    Expense getExpenseById(Long id);
    Expense updateExpense(Long id, Expense expense);
    void deleteExpenseById(Long id);
    List<Expense> getExpensesByCategory(CategoryEnum category);

    //TODO: get expenses by date
    //TODO: get expenses by payment method
    //TODO: generated report by category and period
    //TODO: get expenses of month
}
