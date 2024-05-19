package org.example.library.models;

import jakarta.persistence.*;
<<<<<<< HEAD
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
=======
import jakarta.validation.constraints.*;
>>>>>>> 02279d6632ef216c8b3235455d08011e3d236b9b

import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Введите ФИО")
    @Size(min = 8, max = 100, message = "ФИО может содержать от 8 до 100 символов")
    @Column(name = "full_name")
    private String fullName;

    @Min(value = 1920, message = "Год рождения должен быть не меньше 1920")
    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person() {}

    public Person(String fullName, int yearOfBirth, List<Book> books) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
