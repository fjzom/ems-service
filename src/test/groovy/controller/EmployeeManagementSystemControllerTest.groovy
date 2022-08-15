package controller

import com.example.emsservice.controller.EmployeeManagementSystemController
import com.example.emsservice.domain.Employee
import com.example.emsservice.repository.EmployeeRepositoryJdbcTemplate
import com.example.emsservice.repository.IEmployeeRepositoryJpa
import spock.lang.Specification

class EmployeeManagementSystemControllerTest extends Specification {
    EmployeeRepositoryJdbcTemplate repo = Mock();
    EmployeeManagementSystemController employeeManagementSystemController = new EmployeeManagementSystemController(repo);

    def "validate getAllEmployee method"() {
        given:
            def employeeList = Arrays.asList(new Employee("something", "", 2.0, 2.0, "lol",  null));
            repo.findAll() >> employeeList
        when:
            def result = employeeManagementSystemController.getAllEmployee()
        then:
            noExceptionThrown()
    }
    def "validate getEmployee method"() {
        given:
            def employee = new Employee("something", "", 2.0, 2.0, "lol", "");
            repo.findById(_) >> employee
        when:
            def result = employeeManagementSystemController.getEmployee("")
        then:
            noExceptionThrown()
    }
    def "validate addEmployee method"() {
        given:
            def employee = new Employee("something", "", 2.0, 2.0, "lol", "");
            repo.save(_) >> 1
        when:
            def result = employeeManagementSystemController.addEmployees(employee)
        then:
            noExceptionThrown()
    }
    def "validate editEmployees method"() {
        given:
            def employee = new Employee("something", "", 2.0, 2.0, "lol", "");
            repo.save("1", employee) >> 1
        when:
            def result = employeeManagementSystemController.editEmployees("1", employee)
        then:
            noExceptionThrown()
    }

    def "validate patchEmployees method"() {
        given:
            def employee = new Employee("something", "", 2.0, 2.0, "lol", "");
            repo.save("1", 2.0, "address test") >> 1
        when:
            def result = employeeManagementSystemController.patchEmployees("1", 2.0, "address test")
        then:
            noExceptionThrown()
    }
    def "validate deleteEmployees method"() {
        given:
            def employee = new Employee("something", "", 2.0, 2.0, "lol", "");
            repo.save("1", 2.0, "address test") >> 1
        when:
            def result = employeeManagementSystemController.deleteEmployees("1")
        then:
            noExceptionThrown()
    }

}
