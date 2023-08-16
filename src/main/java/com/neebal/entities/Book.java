package com.neebal.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "books")
//These named queries are used in the Hql in QueryingBooksHql.java
@NamedQueries({
        @NamedQuery(
                name = "Book.findTitlePrice",
                query = "select b.title,b.price from Book b"
        ),
        @NamedQuery(
                name = "Book.findTitlePriceByPagesOrderByPrice",
                query = "select b.title,b.price from Book b " +
                        "where b.pages> :pages " +
                        "order by b.price desc"
        )
})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private Integer pages;

    @Column(nullable = true)
    private Double price;

    @ManyToOne
    private PublicationHouse publicationHouse;

    @ManyToMany(mappedBy = "books")
    private Set<Author> authors;

    @OneToMany(mappedBy = "book")
    private  Set<BookStudent> studentsIssued;


    public Book() {}

    public Book(String title, Integer pages, Double price) {
        this.title = title;
        this.pages = pages;
        this.price = price;
    }

    /*public Book(String title, Integer pages, Double price, PublicationHouse publicationHouse) {
        this.title = title;
        this.pages = pages;
        this.price = price;
        this.publicationHouse = publicationHouse;
    }*/

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<BookStudent> getStudentsIssued() {
        return studentsIssued;
    }

    public void setStudentsIssued(Set<BookStudent> studentsIssued) {
        this.studentsIssued = studentsIssued;
    }

    public PublicationHouse getPublicationHouse() {
        return publicationHouse;
    }

    public void setPublicationHouse(PublicationHouse publicationHouse) {
        this.publicationHouse = publicationHouse;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", pages=" + pages +
                ", price=" + price +
                '}';
    }
}