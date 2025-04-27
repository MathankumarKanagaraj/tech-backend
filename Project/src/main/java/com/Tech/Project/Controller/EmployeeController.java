package com.Tech.Project.Controller;
import com.Tech.Project.Dto.ResponseDto;
import com.Tech.Project.Entity.Employee;
import com.Tech.Project.Service.EmployeeService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public ResponseDto createEmployee(@RequestBody Employee employee) {
        return this.employeeService.createEmployee(employee);
    }

    @GetMapping("/employee/{id}")
    public ResponseDto findByEmployee(@PathVariable final String id) throws BadRequestException {
        return this.employeeService.findByEmployee(id);
    }

    @GetMapping("/employee")
    public ResponseDto retrieveEmployees() {
        return this.employeeService.retrieveEmployees();
    }

    @PutMapping("/employee/{id}")
    public ResponseDto updateByEmployee(@PathVariable final String id, @RequestBody Employee employee) throws BadRequestException {
        return this.employeeService.updateByEmployee(id, employee);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseDto deleteByEmployee(@PathVariable final String id) throws BadRequestException {
        return this.employeeService.deleteByEmployee(id);
    }
}

