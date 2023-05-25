package com.spia.readinglist.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String reader;
    private String isbn;
    private String title;
    private String author;
    private String description;

}
