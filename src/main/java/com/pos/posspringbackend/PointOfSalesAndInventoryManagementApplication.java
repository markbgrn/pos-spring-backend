package com.pos.posspringbackend;

import com.pos.posspringbackend.employee.entity.Employee;
import com.pos.posspringbackend.employee.entity.Gender;
import com.pos.posspringbackend.employee.repository.EmployeeRepository;
import com.pos.posspringbackend.user.entity.User;
import com.pos.posspringbackend.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Calendar;

import static com.pos.posspringbackend.user.enumerated.Role.ADMIN;

@SpringBootApplication
public class PointOfSalesAndInventoryManagementApplication implements CommandLineRunner {
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    public PointOfSalesAndInventoryManagementApplication(EmployeeRepository employeeRepository,
                                                         UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
    }
    public static void main(String[] args) {
        SpringApplication.run(PointOfSalesAndInventoryManagementApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        // Clean up database tables
        employeeRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();

        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.set(1969, Calendar.JANUARY, 1);
        //=========================================

        // Create a User instance
        User user = new User();
        user.setEmail("admin@mail.com");
        new BCrypt();
        user.setPassword(BCrypt.hashpw("password", BCrypt.gensalt(10)));
        user.setRole(ADMIN);

        // Create a UserProfile instance
        Employee employee = new Employee();
        employee.setFirstName("Johnny");
        employee.setLastName("Sins");
        employee.setGender(Gender.MALE);
        employee.setDateOfBirth(dateOfBirth.getTime());
        employee.setPhone("1234567890");
        employee.setAddress("123 Street");
        employee.setZipCode("6969");
        employee.setUser(user);
        // Set child reference(userProfile) in parent entity(user)
        user.setEmployee(employee);
        // Set parent reference(user) in child entity(userProfile)
//        employee.setUser(user);
        // Save Parent Reference (which will save the child as well)
        userRepository.save(user);
        //=========================================
    }
}

