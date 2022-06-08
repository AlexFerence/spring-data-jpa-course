package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

    // ?1 and ?2 means the first and second parameter
    @Query("SELECT s FROM student s WHERE s.firstName = ?1 AND s.age >= ?2")
    List<Student> findStudentsByFirstNameEqualsAndAgeEquals(String firstName, Integer age);

    @Query(value = "SELECT s FROM student s WHERE s.first_name = ?1 AND s.age >= ?2", nativeQuery = true)
    List<Student> selectStudentsNative(String firstName, Integer age);

    @Query(value = "SELECT s.first_name FROM student s", nativeQuery = true)
    List<String> getAllStudentNames();

    @Query(value = "SELECT s FROM student s WHERE s.first_name = :firstName AND s.age >= :age", nativeQuery = true)
    List<Student> getAllStudents(
            @Param("firstName") String firstName,
            @Param("age") Integer age
    );

    @Transactional
    @Modifying
    @Query("DELETE FROM student s WHERE s.id = ?1")
    int plzDelete(Long id);


}
