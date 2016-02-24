package com.linux.rhcloud.javaee.presentation;

import com.linux.rhcloud.javaee.employee.business.entity.Employee;
import com.linux.rhcloud.javaee.employee.business.entity.Gender;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Backing bean for Index page
 * @author Guruprasad Kulkarni <guru@linux.com>
 */
@Model
public class Index {

    private static final Logger LOG = getLogger(Index.class);
    
    private Employee employee;

    @PostConstruct
    public void init(){
        this.employee = new Employee(new Timestamp(System.currentTimeMillis()), "John", "Doe", Gender.M, new Timestamp(System.currentTimeMillis()));
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public Gender[] getGenderOptions(){
        return Gender.values();
    }
    
    public String getToday(){
        return new SimpleDateFormat("MM/dd/yyyy").format(new Date());
    }
    
    public Object addEmployee(){
        LOG.info("Employee saved as :: {}", employee.getJsonObject());
        return null;
    }

}
