package com.example.monedita.service;

import com.example.monedita.exceptions.ExpenseNotFoundException;
import com.example.monedita.model.Expense;
import com.example.monedita.model.enums.CategoryEnum;
import com.example.monedita.model.enums.PaymentMethodEnum;
import com.example.monedita.repository.IExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService implements IExpenseService {

    @Autowired
    private IExpenseRepository expenseRepository;

    @Override
    public Expense createExpense(Expense expense) {
        if(expense.getDate().isAfter(LocalDate.now())){
            throw new IllegalArgumentException("The expense date cannot be in the future");
        }
        return expenseRepository.save(expense);
    }

    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id).orElseThrow(()->new ExpenseNotFoundException(id));
    }

    @Override
    public Expense updateExpense(Long id, Expense expense) {
        //validamos que exista el gasto
        Expense existingExpense = getExpenseById(id);

        //validacion de negocio, la fecha no puede ser futura
        if(expense.getDate().isAfter(LocalDate.now())){
            throw new IllegalArgumentException("The expense date cannot be in the future");
        }

        //actualizando campos
        existingExpense.setDescription(expense.getDescription());
        existingExpense.setAmount(expense.getAmount());
        existingExpense.setCategory(expense.getCategory());
        existingExpense.setDate(expense.getDate());
        existingExpense.setPaymentMethod(expense.getPaymentMethod());


        return expenseRepository.save(existingExpense);
    }

    @Override
    public void deleteExpenseById(Long id) {
        Expense expense = getExpenseById(id);
        expenseRepository.delete(expense);
    }

    @Override
    public List<Expense> getExpensesByCategory(CategoryEnum category) {
        return expenseRepository.getExpensesByCategory(category);
    }

    @Override
    public List<Expense> getExpensesByDate(LocalDate date) {
        return expenseRepository.getExpensesByDate(date);
    }

    @Override
    public List<Expense> getExpensesByPaymentMethod(PaymentMethodEnum paymentMethod) {
        return expenseRepository.getExpensesByPaymentMethod(paymentMethod);
    }
}
