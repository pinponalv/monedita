package com.example.monedita.exceptions;

public class ExpenseNotFoundException extends RuntimeException{

    public ExpenseNotFoundException(Long id){
        super(String.format("Expense with id %d not found", id));
    }

    public ExpenseNotFoundException(String message) {
        super(message);
    }
}
