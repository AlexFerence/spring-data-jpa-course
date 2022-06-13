package com.example.demo;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity(name = "Book")
@Table(name = "book")
public class Book {
    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    @Column(
            updatable = false
    )
    private long id;

    @Column(
            nullable = false
    )
    private String bookName;

    @Column(
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime createdAt;

    // many books can be assigned to one student?
    @ManyToOne
    @JoinColumn(
            name = "student_id",  
            referencedColumnName = "id"
    )
    private Student studentId;

    public Book() {
    }

    public Book(String bookName) {
        this.bookName = bookName;
        this.createdAt = LocalDateTime.now();
    }

    public Book(String bookName, Student student) {
        this.bookName = bookName;
        this.studentId = student;
        this.createdAt = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
