package org.example.library.services;

import org.example.library.models.Book;
import org.example.library.models.Person;
import org.example.library.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

<<<<<<< HEAD
import java.time.LocalDateTime;
=======
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
>>>>>>> 02279d6632ef216c8b3235455d08011e3d236b9b
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll(boolean sort) {
        if (sort) {
            return booksRepository.findAll(Sort.by("year"));
        } else {
            return booksRepository.findAll();
        }
    }

    public List<Book> findAllWithPagination(Integer page, Integer booksPerPage, boolean sort) {
        if (sort) {
            return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
        } else {
            return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
        }
    }

    public Book findOne(int id) {
        Optional<Book> foundBook = booksRepository.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    public Optional<Person> findOwner(int id) {
        return Optional.ofNullable(findOne(id).getOwner());
    }

    @Transactional
    public void assign(int id, Person selectedPerson) {
        booksRepository.findById(id).ifPresent(book -> {
            book.setTakenAt(LocalDateTime.now());
            book.setOwner(selectedPerson);
        });
    }

    @Transactional
    public void release(int id) {
        booksRepository.findById(id).ifPresent(book -> {
            book.setExpiration(false);
            book.setTakenAt(null);
            book.setOwner(null);
        });
    }

    public List<Book> findByTitleStartingWith(String query) {
        return booksRepository.findByTitleStartingWith(query);
    }
}
