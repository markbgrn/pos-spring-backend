package com.pos.posspringbackend.employee.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pos.posspringbackend.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name ="tbl_employee")
public class Employee{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="first_name", nullable = false, length = 50)
    @NotBlank(message = "First name is required")
    private String firstName;

    @Column(name="last_name", nullable = false, length = 50)
    @NotBlank(message = "Last name is required")
    private String lastName;

    @Column(name = "address", nullable = false, length = 100)
    @NotBlank(message = "Address is required")
    private String address;

    @Column(name = "phone_number")
    @Size(max = 12)
    @NotBlank(message = "Phone number is required")
    private String phone;

    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Gender is required")
    private Gender gender;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    @NotBlank(message = "Date of birth is required")
    private Date dateOfBirth;

    @Column(name = "zip_code")
    @Size(max = 4)
    @NotBlank(message = "Zip code is required")
    private String zipCode;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("employee")
    private User user;
}
