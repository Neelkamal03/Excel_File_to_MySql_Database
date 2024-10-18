package com.example.excel_to_database_springBoot.controller;

import com.example.excel_to_database_springBoot.domain.Customer;
import com.example.excel_to_database_springBoot.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/upload-customer-data")
    public ResponseEntity<?> uploadCustomerData(@RequestParam("file")MultipartFile file){
        customerService.saveCustomerToDatabase(file);
        return ResponseEntity.ok(Map.of("Message", "Customer data Uploaded and saved to database successfully"));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomer(){
        return new ResponseEntity<>(customerService.getAllCustomer(), HttpStatus.OK);
    }
}
