package com.example.excel_to_database_springBoot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    private Integer customerId;
    private String firstName;
    private String lastname;
    private String country;
    private Integer telephone;
}

