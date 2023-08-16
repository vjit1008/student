package com.neebal.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "publications_houses")
public class PublicationHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    private Date establishmentDate;

    //By default this relationship is lazy
    @OneToMany(mappedBy = "publicationHouse", cascade = CascadeType.ALL)
    private Set<Book> books;

    public PublicationHouse() {
    }

    public PublicationHouse(String name, Date establishmentDate) {
        this.name = name;
        this.establishmentDate = establishmentDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(Date establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "PublicationHouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", establishmentDate=" + establishmentDate +
                ", books=" + books +
                '}';
    }
}

