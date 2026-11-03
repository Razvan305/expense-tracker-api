package com.razvan.expensetracker.repositories;

import com.razvan.expensetracker.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e")
    BigDecimal sumAllExpenses();

    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e WHERE e.createdAt >= :startDate AND e.createdAt < :endDate")
    BigDecimal sumExpensesBetween(@Param("startDate")LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    List<Expense> findAllByOrderByCreatedAtDesc();
}
