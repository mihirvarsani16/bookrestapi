package com.bookrestapi.bookrestapi.service;

import java.util.List;

import com.bookrestapi.bookrestapi.entity.Book;
import com.bookrestapi.bookrestapi.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    public BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book add(Book book) {

        return this.bookRepository.save(book);
    }

    public Book getBook(Long id) {

        return this.bookRepository.findByBid(id);
    }

    public Book updateBook(Book book) {

        return this.bookRepository.save(book);
    }

    public List<Book> getAllBook() {

        return this.bookRepository.findAll();
    }

    public void deleteBook(Long id) {

        this.bookRepository.deleteById(id);
    }
}
