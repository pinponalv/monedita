package com.example.monedita.controller;


import com.example.monedita.model.Expense;
import com.example.monedita.model.enums.CategoryEnum;
import com.example.monedita.service.IExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
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

    @DeleteMapping
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

}
