package com.employee.employee.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@ToString @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "psql_unique_id")
    @SequenceGenerator(name = "psql_unique_id", sequenceName = "employee_id")
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

}
