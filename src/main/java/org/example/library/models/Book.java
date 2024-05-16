package org.example.library.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Введите название книги")
    @Size(min = 1, max = 100, message = "Название книги может содержать от 1 до 100 символов")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "Введите автора")
    @Size(min = 2, max = 100, message = "Имя автора может содержать от 2 до 100 символов")
    @Column(name = "author")
    private String author;

    @Min(value = 1500, message = "Книга не может быть старше 1500 года")
    @Column(name = "year")
    private int year;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    @Column(name = "taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime takenAt;

    @Transient
    private boolean expiration = false;

    public Book() {}

    public Book(String title, String author, int year, Person owner, LocalDateTime takenAt) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.owner = owner;
        this.takenAt = takenAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public LocalDateTime getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(LocalDateTime takenAt) {
        this.takenAt = takenAt;
    }

    public boolean isExpiration() {
        return expiration;
    }

    public void setExpiration(boolean expiration) {
        this.expiration = expiration;
    }
}
