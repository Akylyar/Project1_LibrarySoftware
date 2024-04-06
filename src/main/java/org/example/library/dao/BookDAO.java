package org.example.library.dao;

import org.example.library.models.Book;
import org.example.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooks() {
        return jdbcTemplate.query("SELECT * FROM \"library\".book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM \"library\".book WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO \"library\".book(title, author, year) VALUES(?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYear());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE \"library\".book SET title=?, author=?, year=? WHERE id=?",
                updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM \"library\".book WHERE id=?", id);
    }

    public void assign(int selectedPerson, int id) {
        jdbcTemplate.update("UPDATE \"library\".book SET person_id=? WHERE id=?", selectedPerson, id);
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE \"library\".book SET person_id=NULL WHERE id=?", id);
    }

    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("SELECT * FROM \"library\".person p JOIN \"library\".book b ON p.id=b.person_id WHERE b.id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }
}
