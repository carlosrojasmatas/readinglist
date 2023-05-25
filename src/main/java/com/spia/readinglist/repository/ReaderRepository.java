package com.spia.readinglist.repository;

import com.spia.readinglist.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;


interface ReaderRepository extends JpaRepository<Reader, String> {
}

