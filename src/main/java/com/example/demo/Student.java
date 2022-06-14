package com.example.demo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "student")
public class Student {
    // Have the id start at 1 and increase with each record
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    @Column(
        updatable = false
    )
    private long id;

    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;
    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;
    @Column(
            nullable = false,
            columnDefinition = "TEXT",
            unique = true
    )
    private String email;
    @Column(
            nullable = false
    )
    private Integer age;

    // StudentIDCard is the owning entity
    // Shows that it is loaded by the studentIdCard
    @OneToOne(
            mappedBy = "student",
            orphanRemoval = true
    )
    private StudentIdCard studentIdCard;

    // Should be two
    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private List<Enrolment> enrolments = new ArrayList<>();

    public Student(String firstName, String lastName, String email, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Student() {};

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void addEnrolment(Enrolment enrolment) {
        if (!enrolments.contains(enrolment)) {
            enrolments.add(enrolment);
        }
    }

    public void removeEnrolment(Enrolment enrolment) {
        enrolments.remove(enrolment);
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
