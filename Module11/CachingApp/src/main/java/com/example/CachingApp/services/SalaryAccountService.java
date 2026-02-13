package com.example.CachingApp.services;

import com.example.CachingApp.entities.Employee;
import com.example.CachingApp.entities.SalaryAccount;

public interface SalaryAccountService {
    void createAccount(Employee employee);

    SalaryAccount incrementBalance(Long accountId);
}
