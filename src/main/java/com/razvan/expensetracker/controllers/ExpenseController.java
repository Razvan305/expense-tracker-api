package com.razvan.expensetracker.controllers;

import com.razvan.expensetracker.dtos.ExpenseRequestDto;
import com.razvan.expensetracker.dtos.ExpenseResponseDto;
import com.razvan.expensetracker.services.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ExpenseResponseDto> createExpense(@RequestBody ExpenseRequestDto requestDto) {
        ExpenseResponseDto created = expenseService.addExpense(requestDto.getDescription(), requestDto.getAmount());
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponseDto>> getAllExpenses(@RequestParam(required = false) String description, BigDecimal amount) {
        List<ExpenseResponseDto> expenses = expenseService.getAllExpenses();
        if (expenses == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(expenses);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ExpenseResponseDto> updateExpense(
            @PathVariable Long id,
            @RequestBody ExpenseRequestDto requestDto) {
        ExpenseResponseDto update = expenseService.updateExpense(id, requestDto.getDescription(), requestDto.getAmount());
        if (update == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ExpenseResponseDto> deleteExpense(@PathVariable Long id) {
        ExpenseResponseDto deleted = expenseService.deleteExpense(id);
        if (deleted == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("/summary")
    public ResponseEntity<BigDecimal> getTotalSummary() {
        return ResponseEntity.ok(expenseService.getTotalSummary());
    }

    @GetMapping("/summary/monthly")
    public ResponseEntity<BigDecimal> getMonthlySummary(@RequestParam int month) {
        return ResponseEntity.ok(expenseService.getMonthlySummary(month));
    }
}
