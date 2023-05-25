package com.spia.readinglist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Reader;

interface ReaderRepository extends JpaRepository<Reader, String> {
}

