package com.company.bookservice.dao;

import com.company.bookservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BookDao extends JpaRepository <Book, Integer> {




}
