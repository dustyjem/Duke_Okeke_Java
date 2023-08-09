package com.company.bookstore.controller;


import com.company.bookstore.model.Author;
import com.company.bookstore.model.Book;
import com.company.bookstore.model.Publisher;
import com.company.bookstore.repository.AuthorRepository;
import com.company.bookstore.repository.BookRepository;
import com.company.bookstore.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class GraphController {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    PublisherRepository publisherRepository;

    //Books
    @QueryMapping
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @QueryMapping
    public Book findBookById(@Argument int id) {
       List<Book> idMatch = this.findAllBooks();
       Book result = null;
         for (Book b : idMatch) {
              if (b.getId() == id) {
                result = b;
              }
         }
            return result;
    }

    //Authors
    @QueryMapping
    public List<Book> findAllAuthors() {
        return bookRepository.findAll();
    }

    @QueryMapping
    public Author findBookByAuthorId(@Argument int id) {
        Optional<Author> author = authorRepository.findById(id);
        return author.orElse(null);
    }

    //Publishers
    @QueryMapping
    public List<Publisher> findAllPublishers() {
        return publisherRepository.findAll();
    }

    @QueryMapping
    public Publisher findPublisherById(@Argument int id) {
        Optional<Publisher> publisher = publisherRepository.findById(id);
        return publisher.orElse(null);
    }

    //Schema for book query
    @SchemaMapping
    public Publisher publisher(Book book) {
        return publisherRepository.findById(book.getPublisherId()).orElse(null);
    }

    @SchemaMapping
    public Author author(Book book) {
        return authorRepository.findById(book.getAuthorId()).orElse(null);
    }


//    @MutationMapping
//    public Book addBook(@Argument String title,
//                        @Argument int authorId,
//                        @Argument int publisherId,
//                        @Argument String publishDate,
//                        @Argument String isbn,
//                        @Argument Float price) {
//        Book newBook = new Book(title, authorId, publisherId, publishDate, isbn, price);
//        return bookRepository.save(newBook);
//    }
//
//    @MutationMapping
//    public Author addAuthor(@Argument String firstName,
//                            @Argument String lastName,
//                            @Argument String street,
//                            @Argument String city,
//                            @Argument String state,
//                            @Argument String postalCode,
//                            @Argument String phone,
//                            @Argument String email) {
//        Author newAuthor = new Author(firstName, lastName, street, city, state, postalCode, phone, email);
//        return authorRepository.save(newAuthor);
//    }
//
//    @MutationMapping
//    public Publisher addPublisher(@Argument String name,
//                                  @Argument String street,
//                                  @Argument String city,
//                                  @Argument String state,
//                                  @Argument String postalCode,
//                                  @Argument String phone,
//                                  @Argument String email) {
//        Publisher newPublisher = new Publisher(name, street, city, state, postalCode, phone, email);
//        return publisherRepository.save(newPublisher);
//    }

//    @MutationMapping
//    public void deleteBook(@Argument int id) {
//        bookRepository.deleteById(id);
//    }
//
//    @MutationMapping
//    public void deleteAuthor(@Argument int id) {
//        authorRepository.deleteById(id);
//    }
//
//    @MutationMapping
//    public void deletePublisher(@Argument int id) {
//        publisherRepository.deleteById(id);
//    }

}
