package com.bookrestapi.bookrestapi.controller;

import java.util.List;

import com.bookrestapi.bookrestapi.entity.Book;
import com.bookrestapi.bookrestapi.helper.BookIdMismatchException;
import com.bookrestapi.bookrestapi.helper.BookNotFoundException;
import com.bookrestapi.bookrestapi.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/")
    // @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> add(@RequestBody Book book) {

        if (book.getAuthor() == null || book.getTitle() == null) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("please add all details");
        } else {

            Book book1 = this.bookService.add(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(book1);
        }

    }

    @PutMapping("/")
    public ResponseEntity<Book> update(@RequestBody Book book) throws Exception {
        Book book1 = this.bookService.getBook(book.getBid());

        if (book1 == null) {

            throw new BookNotFoundException();

        } else {

            // .orElseThrow(BookNotFoundException::new);
            Book book2 = this.bookService.updateBook(book);
            return ResponseEntity.status(HttpStatus.OK).body(book2);
        }
    }

    @GetMapping("/{bid}")
    // @ExceptionHandler(BookNotFoundException.class)
    // BookNotFoundException ex
    public ResponseEntity<?> getBook(@PathVariable Long bid) throws Exception {

        Book book = this.bookService.getBook(bid);

        if (book == null) {

            throw new BookNotFoundException();
            // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } else {

            return ResponseEntity.status(HttpStatus.FOUND).body(book);
        }
    }

    @GetMapping("/all-book")
    public ResponseEntity<?> getAllBook() {
        List<Book> book = this.bookService.getAllBook();

        if (book == null) {

            String message = "there is no book avalible";
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(book);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        Book book = this.bookService.getBook(id);
        if (book == null) {
            throw new BookIdMismatchException("this id not match with any book available in data");
        } else {
            this.bookService.deleteBook(id);

            String message = "successfully deleted";

            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    }

    // @ExceptionHandler(BookNotFoundException.class)
    // public ResponseEntity<?> ExceptionHandler(BookNotFoundException ex) {

    // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    // }
}
