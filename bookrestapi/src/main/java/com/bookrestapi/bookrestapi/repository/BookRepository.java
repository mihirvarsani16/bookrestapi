package com.bookrestapi.bookrestapi.repository;

import com.bookrestapi.bookrestapi.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {

    public Book findByBid(Long id);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN TRUE ELSE FALSE END FROM Book s WHERE s.bid = :id")
    public Boolean isBookExitsByBid(Long id);
}
