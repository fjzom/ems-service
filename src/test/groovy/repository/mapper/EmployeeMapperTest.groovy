package repository.mapper

import com.example.emsservice.repository.Mapper.EmployeeMapper
import spock.lang.Specification

import java.sql.ResultSet

class EmployeeMapperTest extends Specification {
    EmployeeMapper mapper = new EmployeeMapper();
    ResultSet rs = Mock()

    def "mapRow"() {
        given:
            rs.getString("employee_id")>> "1"
            rs.getString("address")>> "1"
            rs.getString("emp_name")>> "1"
            rs.getDouble("height")>> 2.0
            rs.getDouble("weight")>> 2.0
            rs.getString("employee_salary_fk") >> "1"

        when:
            mapper.mapRow(rs, 1);
        then:
            noExceptionThrown()
    }

}
