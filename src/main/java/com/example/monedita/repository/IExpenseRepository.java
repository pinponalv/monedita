package com.example.monedita.repository;

import com.example.monedita.model.Expense;
import com.example.monedita.model.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> getExpensesByCategory(CategoryEnum category);

    //TODO: create more methods for search
}
