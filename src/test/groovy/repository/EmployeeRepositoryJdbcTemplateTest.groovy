package repository

import com.example.emsservice.domain.Employee
import com.example.emsservice.repository.EmployeeRepositoryJdbcTemplate
import com.example.emsservice.repository.Mapper.EmployeeMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import spock.lang.Specification

class EmployeeRepositoryJdbcTemplateTest extends Specification {
    NamedParameterJdbcTemplate jdbcTemplate = Mock()
    EmployeeMapper mapper = Mock()
    EmployeeRepositoryJdbcTemplate empRepo = new EmployeeRepositoryJdbcTemplate(jdbcTemplate, mapper);

    def 'validate findAll method '() {
        given:
            def employeeList = Arrays.asList(new Employee("something", "", 2.0, 2.0, "lol", ""));
        when:
            jdbcTemplate.query(_, _) >> employeeList
        then:
            empRepo.findAll()
        and:
            noExceptionThrown()
    }

    def 'validate findById '() {
        given:
            def employeeList = Arrays.asList(new Employee("something", "", 2.0, 2.0, "lol", ""));
        when:
            jdbcTemplate.query(_, _) >> employeeList
        then:
            empRepo.findById("1")
        and:
            noExceptionThrown()
    }

    def 'validate insert employee  '() {
        given:
            def emp = new Employee("something", "", 2.0, 2.0, "lol", "")
        when:
            1 * jdbcTemplate.update(_, _) >> 1
        then:
            def result = empRepo.save(emp)
        and:
            noExceptionThrown()
            result == 1
    }

    def 'validate edit employee  '() {
        given:
            def emp = new Employee("something", "", 2.0, 2.0, "lol", "")
        when:
            1 * jdbcTemplate.update(_, _) >> 1
        then:
            def result = empRepo.save("1", emp)
        and:
            noExceptionThrown()
            result == 1
    }

    def 'validate patch employee  '() {
        when:
            1 * jdbcTemplate.update(_, _) >> 1
        then:
            def result = empRepo.save("1", 2.0, " address test")
        and:
            noExceptionThrown()
            result == 1
    }
    def 'validate delete employee  '() {
        when:
            1 * jdbcTemplate.update(_, _) >> 1
        then:
            def result = empRepo.delete("1")
        and:
            noExceptionThrown()
            result == 1
    }
}
