package com.example.excel_to_database_springBoot.repository;

import com.example.excel_to_database_springBoot.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
