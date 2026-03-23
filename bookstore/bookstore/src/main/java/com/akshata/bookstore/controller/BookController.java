package com.akshata.bookstore.controller;

import com.akshata.bookstore.model.Book;
import com.akshata.bookstore.repository.BookRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Page<Book> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return repository.findAll(PageRequest.of(page, size, Sort.by(sortBy)));
    }

    // POST add new book
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return repository.save(book);
    }

    // DELETE book by id
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
    }
    
    
    @GetMapping("/author/{author}")
    public List<Book> getBooksByAuthor(@PathVariable String author) {
        return repository.findByAuthor(author);
    }
}