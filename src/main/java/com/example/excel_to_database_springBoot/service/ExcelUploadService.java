package com.example.excel_to_database_springBoot.service;

import com.example.excel_to_database_springBoot.domain.Customer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ExcelUploadService {
    //Static method we can directly call this method without creating instance of class
    public static boolean isValidExcelFile(MultipartFile file){
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }
    public static List<Customer> getCustomersDataFromExcel(InputStream inputStream){
        List<Customer> customers=new ArrayList<>();
       // XSSFWorkbook,
        // you can create, read, and modify Excel files.
        // This includes adding sheets, rows, and cells,
        // and populating them with data.
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet =workbook.getSheetAt(0);

            int rowIndex=0;
            for(Row row:sheet){
                if(rowIndex==0){
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator=row.iterator();
                int cellIndex=0;
                Customer customer=new Customer();
                while(cellIterator.hasNext()){
                    Cell cell=cellIterator.next();
                    switch(cellIndex){
                        case 0 -> {
                            if (cell.getCellType() == CellType.NUMERIC) {
                                customer.setCustomerId((int) cell.getNumericCellValue());
                            } else {
                                throw new IllegalArgumentException("Invalid data type for Customer ID");
                            }
                        }
                        case 1 ->customer.setFirstName(cell.getStringCellValue());
                        case 2-> customer.setLastname(cell.getStringCellValue());
                        case 3->customer.setCountry(cell.getStringCellValue());
                        case 4 ->customer.setTelephone((int)cell.getNumericCellValue());
                        default -> {

                        }
                    }
                    cellIndex++;
                }
                customers.add(customer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }
}
