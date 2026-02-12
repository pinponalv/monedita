package com.example.monedita.controller;


import com.example.monedita.entity.Expense;
import com.example.monedita.entity.enums.CategoryEnum;
import com.example.monedita.entity.enums.PaymentMethodEnum;
import com.example.monedita.service.IExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@PreAuthorize("authenticated()")
public class ExpenseController {

    @Autowired
    private IExpenseService expenseService;

    @PostMapping
    public ResponseEntity<Expense> createExpense(@Valid @RequestBody Expense expense){
        Expense newExpense = expenseService.createExpense(expense);
        //return http 201 created
        return ResponseEntity.status(HttpStatus.CREATED).body(newExpense);
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses(){
        List<Expense> expenses = expenseService.getAllExpenses();
        //return http 200
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id){
        Expense expense = expenseService.getExpenseById(id);
        //return http 200
        return ResponseEntity.ok(expense);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpenseById(@PathVariable Long id, @Valid @RequestBody Expense expense){
        Expense updatedExpense = expenseService.updateExpense(id, expense);
        //return http 200
        return ResponseEntity.ok(updatedExpense);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Expense> deleteExpenseById(@PathVariable Long id){
        expenseService.deleteExpenseById(id);
        //return http 204 for delete or put/patch
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Expense>> getExpensesByCategory(@PathVariable CategoryEnum category){
        List<Expense> expenses = expenseService.getExpensesByCategory(category);
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Expense>> getExpensesByDate(@PathVariable LocalDate date){
        List<Expense> expenses = expenseService.getExpensesByDate(date);
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/paymenth/{paymentMethod}")
    public ResponseEntity<List<Expense>> getExpensesByPaymenth(@PathVariable PaymentMethodEnum paymentMethod){
        List<Expense> expenses = expenseService.getExpensesByPaymentMethod(paymentMethod);
        return ResponseEntity.ok(expenses);
    }

}
