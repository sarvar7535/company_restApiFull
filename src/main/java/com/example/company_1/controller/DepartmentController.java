package com.example.company_1.controller;

import com.example.company_1.payload.ApiResponse;
import com.example.company_1.payload.DepartmentDto;
import com.example.company_1.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/getAllDepartment")
    public ResponseEntity<ApiResponse> getAllDepartment(){
        ApiResponse allDepartment = departmentService.getAllDepartment();
        return ResponseEntity.status(200).body(allDepartment);
    }

    @GetMapping("/getDepartmentById/{id}")
    public ResponseEntity<ApiResponse> getDepartmentById(@PathVariable Integer id){
        ApiResponse departmentById = departmentService.getDepartmentById(id);
        if (departmentById.isSuccess()){
            return ResponseEntity.status(200).body(departmentById);
        }else {
            return ResponseEntity.status(404).body(departmentById);
        }
    }

    @GetMapping("/getDepartmentsPageable/{companyId}")
    public ResponseEntity<ApiResponse> getDepartmentPageable(@PathVariable Integer companyId, @RequestParam int pageNumber){
        ApiResponse departmentPageable = departmentService.getDepartmentPageable(companyId, pageNumber);
        return ResponseEntity.status(200).body(departmentPageable);
    }

    @DeleteMapping("/deleteDepartment/{id}")
    public ResponseEntity<ApiResponse> deleteDepartment(@PathVariable Integer id){
        ApiResponse apiResponse = departmentService.deleteDepartment(id);
        if (apiResponse.isSuccess()){
            return ResponseEntity.status(200).body(apiResponse);
        }else {
            return ResponseEntity.status(404).body(apiResponse);
        }
    }

    @PostMapping("/saveNewDepartment")
    public ResponseEntity<ApiResponse> saveNewDepartment(@Valid @RequestBody DepartmentDto departmentDto){
        ApiResponse apiResponse = departmentService.saveNewDepartment(departmentDto);
        if (apiResponse.isSuccess()){
            return ResponseEntity.status(201).body(apiResponse);
        }else {
            return ResponseEntity.status(409).body(apiResponse);
        }
    }

    @PutMapping("/editDepartment/{id}")
    public ResponseEntity<ApiResponse> editDepartment(@PathVariable Integer id,@Valid @RequestBody DepartmentDto departmentDto){
        ApiResponse apiResponse = departmentService.editDepartment(id, departmentDto);
        if (apiResponse.isSuccess()){
            return ResponseEntity.status(201).body(apiResponse);
        }else {
            return ResponseEntity.status(404).body(apiResponse);
        }
    }



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}


