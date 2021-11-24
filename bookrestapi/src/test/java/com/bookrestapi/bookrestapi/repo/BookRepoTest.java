package com.bookrestapi.bookrestapi.repo;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.bookrestapi.bookrestapi.entity.Book;
import com.bookrestapi.bookrestapi.repository.BookRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookRepoTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void isBookExitsByBid() {

        Book b = new Book(27, "the don", "ronda rao");
        bookRepository.save(b);

        Boolean actualResult = bookRepository.isBookExitsByBid(b.getBid());
        assertThat(actualResult).isTrue();
    }

    @AfterEach
    void tearDown() {
        System.out.println("tearing down");
        // Long id = (long) 19;
        // this.bookRepository.deleteById(id);
    }

    @BeforeEach
    void setUp() {
        System.out.println("setting up");
    }

}
