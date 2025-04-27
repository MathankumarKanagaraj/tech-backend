package com.Tech.Project.Service;

import com.Tech.Project.Dto.ResponseDto;
import com.Tech.Project.Entity.Employee;
import com.Tech.Project.Repository.EmployeeRepository;
import com.Tech.Project.Utils.Constants;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public ResponseDto createEmployee(Employee employee) {
        return new ResponseDto(Constants.CREATED, this.employeeRepository.save(employee), HttpStatus.CREATED.value());
    }

    public ResponseDto findByEmployee(String id) throws BadRequestException {
        Optional<Employee> employee = Optional.ofNullable(this.employeeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Employee not found.")));
        return new ResponseDto(Constants.RETRIEVED, employee, HttpStatus.OK.value());
    }

    public ResponseDto retrieveEmployees() {
        return new ResponseDto(Constants.RETRIEVED, this.employeeRepository.findAll(), HttpStatus.OK.value());
    }

    public ResponseDto updateByEmployee(String id, Employee updatedEmployee) throws BadRequestException {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Employee not found."));
        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setPhoneNumber(updatedEmployee.getPhoneNumber());
        existingEmployee.setAge(updatedEmployee.getAge());
        existingEmployee.setGender(updatedEmployee.getGender());
        existingEmployee.setLocation(updatedEmployee.getLocation());
        Employee savedEmployee = employeeRepository.save(existingEmployee);
        return new ResponseDto(Constants.UPDATED, savedEmployee, HttpStatus.OK.value());
    }

    public ResponseDto deleteByEmployee(String id) throws BadRequestException {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Employee not found."));
        this.employeeRepository.delete(employee);
        return new ResponseDto(Constants.DELETED, employee, HttpStatus.OK.value());
    }
}
