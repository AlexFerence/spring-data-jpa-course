package com.example.demo;

import javax.persistence.*;

@Entity(name = "StudentIdCard")
@Table(name = "student_id_card")
public class StudentIdCard {
    @Id
    @SequenceGenerator(
            name = "student_card_id_sequence",
            sequenceName = "student_card_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_card_id_sequence"
    )
    @Column(
            updatable = false
    )
    private long id;

    @Column(
            unique = true,
            nullable = false,
            length = 15
    )
    private String cardNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id"
    )
    private Student student;

    public StudentIdCard() {
    }

    public StudentIdCard(long id, String cardNumber) {
        this.id = id;
        this.cardNumber = cardNumber;
    }

    public StudentIdCard(String cardNumber, Student student) {
        this.cardNumber = cardNumber;
        this.student = student;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "StudentIdCard{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }
}
