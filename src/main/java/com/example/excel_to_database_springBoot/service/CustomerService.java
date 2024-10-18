package com.example.excel_to_database_springBoot.service;

import com.example.excel_to_database_springBoot.domain.Customer;
import com.example.excel_to_database_springBoot.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public void saveCustomerToDatabase(MultipartFile file){
        if(ExcelUploadService.isValidExcelFile(file)){
            try {
                List<Customer> customer=ExcelUploadService.getCustomersDataFromExcel(file.getInputStream());
                customerRepository.saveAll(customer);
            } catch (IOException e) {
                throw new IllegalArgumentException("The file is not a valid excel file");
            }
        }

    }

    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }
}
