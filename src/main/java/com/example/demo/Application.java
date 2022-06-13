package com.example.demo;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository,
            StudentIdCardRepository studentIdCardRepository,
            BookRepository bookRepository
            ) {
        return args -> {
            Faker faker = new Faker();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            Student newStudent = new Student(
                    firstName,
                    lastName,
                    firstName + lastName + "@gmail.com",
                    faker.number().numberBetween(18, 55)
            );

            StudentIdCard studentIdCard = new StudentIdCard("12345", newStudent);

            studentIdCardRepository.save(studentIdCard);


            newStudent.addBook(new Book("Harry Potter"));

            studentIdCardRepository.findById(studentIdCard.getId())
                    .ifPresent(System.out::println);









        };
    }

    private void sorting(StudentRepository studentRepository) {
        Sort sort = Sort.by(Sort.Direction.ASC, "firstName");
        studentRepository.findAll(sort)
                .forEach(student -> System.out.println(student.getFirstName()));
    }

    private void generateRandomStudents(StudentRepository studentRepository) {
        Faker faker = new Faker();
        for (int i = 0; i < 50; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            Student newStudent = new Student(
                    firstName,
                    lastName,
                    firstName + lastName + "@gmail.com",
                    faker.number().numberBetween(18, 55)
            );
            studentRepository.save(newStudent);
        }
    }

}
