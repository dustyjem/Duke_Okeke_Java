package com.company.bookstore.repository;

import com.company.bookstore.model.Author;
import com.company.bookstore.model.Book;
import com.company.bookstore.model.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PublisherRepository publisherRepository;

    @BeforeEach
    public void setUp() throws Exception {
        authorRepository.deleteAll();
        bookRepository.deleteAll();
        publisherRepository.deleteAll();
    }

    @Test
    public void shouldAddBook() {
        // Arrange
        Author author = new Author();
        author.setCity("Pens York");
        author.setEmail("penyo@mail.com");
        author.setPhone("111-111-1111");
        author.setFirstName("Penyo");
        author.setLastName("Doe");
        author.setPostalCode("11111");
        author.setState("NY");
        author.setStreet("Author lane");
        author = authorRepository.save(author);

        Publisher publisher = new Publisher();
        publisher.setCity("Baltimore");
        publisher.setEmail("bali@gmail.com");
        publisher.setName("McGrawHill");
        publisher.setPhone("111-111-1111");
        publisher.setPostalCode("11111");
        publisher.setState("MD");
        publisher.setStreet("M Lane");
        publisher = publisherRepository.save(publisher);

        Book book = new Book();
        book.setAuthorId(author.getId());  // Use the actual author ID generated
        book.setPublisherId(publisher.getId());  // Use the actual publisher ID generated
        book.setTitle("Sample Book Title");
        book.setIsbn("1-11-111111-1");
        book.setPublishDate(LocalDate.of(2022, 8, 1));
        book.setPrice(new BigDecimal("19.99"));
        book = bookRepository.save(book);


        // just a test to get a status

        //Assert
        assertNotNull(book.getId());
        assertEquals("Sample Book Title", book.getTitle());

    }

    @Test
    public void shouldGetBookById() {
        // Arrange
        Author author = new Author();
        author.setCity("New York");
        author.setEmail("author@gmail.com");
        author.setPhone("111-111-1111");
        author.setFirstName("John");
        author.setLastName("Doe");
        author.setPostalCode("11111");
        author.setState("NY");
        author.setStreet("Author lane");
        author = authorRepository.save(author);

        Publisher publisher = new Publisher();
        publisher.setCity("Baltimore");
        publisher.setEmail("pub@gmail.com");
        publisher.setName("McGrawHill");
        publisher.setPhone("111-111-1111");
        publisher.setPostalCode("11111");
        publisher.setState("MD");
        publisher.setStreet("M Lane");
        publisher = publisherRepository.save(publisher);

        Book book = new Book();
        book.setAuthorId(author.getId());  // Use the actual author ID generated
        book.setPublisherId(publisher.getId());  // Use the actual publisher ID generated
        book.setTitle("Sample Book Title");
        book.setIsbn("1-11-111111-1");
        book.setPublishDate(LocalDate.of(2022, 8, 1));
        book.setPrice(new BigDecimal("19.99"));
        book = bookRepository.save(book);

        // Act
        Book retrievedBook = bookRepository.findById(book.getId()).orElse(null);

        // Assert
        assertNotNull(retrievedBook);
        assertEquals("Sample Book Title", retrievedBook.getTitle());
        assertEquals("1-11-111111-1", retrievedBook.getIsbn());
        assertEquals(LocalDate.of(2022, 8, 1), retrievedBook.getPublishDate());
        assertEquals(new BigDecimal("19.99"), retrievedBook.getPrice());
        assertEquals(author.getId(), retrievedBook.getAuthorId());
        assertEquals(publisher.getId(), retrievedBook.getPublisherId());
    }


    @Test
    public void shouldGetAllBooks() {

        // Arrange
        // Create a Publisher and Author
        Publisher publisher = new Publisher();
        publisher.setCity("Baltimore");
        publisher.setEmail("pub@gmail.com");
        publisher.setName("McGrawHill");
        publisher.setPhone("111-111-1111");
        publisher.setPostalCode("11111");
        publisher.setState("MD");
        publisher.setStreet("M Lane");
        publisher = publisherRepository.save(publisher);

        Author author = new Author();
        author.setCity("New York");
        author.setEmail("author@gmail.com");
        author.setPhone("111-111-1111");
        author.setFirstName("John");
        author.setLastName("Doe");
        author.setPostalCode("11111");
        author.setState("NY");
        author.setStreet("Author lane");
        author = authorRepository.save(author);


        // Act..
        Book book = new Book();
        book.setAuthorId(author.getId());
        book.setPublisherId(publisher.getId());
        book.setTitle("testBook1");
        book.setIsbn("1-11-111111-1");
        book.setPublishDate(LocalDate.of(2011, 5, 1));
        book.setPrice(new BigDecimal("1.11"));
        book.setId(author.getId());

        book = bookRepository.save(book);

        Book book2 = new Book();
        book2.setAuthorId(author.getId());
        book2.setPublisherId(publisher.getId());
        book2.setTitle("testBook2");
        book2.setIsbn("2-22-222222-2");
        book2.setPublishDate(LocalDate.of(2012, 4, 5));
        book2.setPrice(new BigDecimal("2.22"));
        book2.setId(author.getId());

        book2 = bookRepository.save(book2);

        // Assert...
        List<Book> bookList = bookRepository.findAll();

        assertEquals(bookList.size(), 2);
    }

    @Test
    public void shouldUpdateBook() {

        // Arrange..
        // Need to create a Publisher and Author first
        Publisher publisher = new Publisher();
        publisher.setCity("Baltimore");
        publisher.setEmail("pub@gmail.com");
        publisher.setName("McGrawHill");
        publisher.setPhone("111-111-1111");
        publisher.setPostalCode("11111");
        publisher.setState("MD");
        publisher.setStreet("M Lane");
        publisher = publisherRepository.save(publisher);

        Author author = new Author();
        author.setCity("New York");
        author.setEmail("author@gmail.com");
        author.setPhone("111-111-1111");
        author.setFirstName("John");
        author.setLastName("Doe");
        author.setPostalCode("11111");
        author.setState("NY");
        author.setStreet("Author lane");
        author = authorRepository.save(author);

        Book book = new Book();
        book.setAuthorId(author.getId());
        book.setPublisherId(publisher.getId());
        book.setTitle("test1");
        book.setIsbn("1111111111111");
        book.setPublishDate(LocalDate.of(2011, 5, 1));
        book.setPrice(new BigDecimal("1.11"));

        book = bookRepository.save(book);

        // Act
        book.setTitle("NewTitle");
        Book updatedBook = bookRepository.save(book);

        // Assert
        assertEquals(book.getId(), updatedBook.getId());
        assertEquals("NewTitle", updatedBook.getTitle());

    }

    @Test
    public void shouldDeleteBook() {

        Publisher publisher = new Publisher();
        publisher.setCity("Baltimore");
        publisher.setEmail("pub@gmail.com");
        publisher.setName("McGrawHill");
        publisher.setPhone("111-111-1111");
        publisher.setPostalCode("11111");
        publisher.setState("MD");
        publisher.setStreet("M Lane");
        publisher = publisherRepository.save(publisher);

        Author author = new Author();
        author.setCity("New York");
        author.setEmail("author@gmail.com");
        author.setPhone("111-111-1111");
        author.setFirstName("John");
        author.setLastName("Doe");
        author.setPostalCode("11111");
        author.setState("NY");
        author.setStreet("Author lane");
        author = authorRepository.save(author);

        Book book = new Book();
        book.setAuthorId(author.getId());
        book.setPublisherId(publisher.getId());
        book.setTitle("test1");
        book.setIsbn("1111111111111");
        book.setPublishDate(LocalDate.of(2011, 5, 1));
        book.setPrice(new BigDecimal("1.11"));

        book = bookRepository.save(book);

        // Act
        bookRepository.deleteById(book.getId());
    }

    @Test
    public void shouldGetBookByAuthorId() {

        // Arrange
        // Arrange
        Author author = new Author();
        author.setCity("New York");
        author.setEmail("author@gmail.com");
        author.setPhone("111-111-1111");
        author.setFirstName("John");
        author.setLastName("Doe");
        author.setPostalCode("11111");
        author.setState("NY");
        author.setStreet("Author lane");
        author = authorRepository.save(author);

        Publisher publisher = new Publisher();
        publisher.setCity("Baltimore");
        publisher.setEmail("pub@gmail.com");
        publisher.setName("McGrawHill");
        publisher.setPhone("111-111-1111");
        publisher.setPostalCode("11111");
        publisher.setState("MD");
        publisher.setStreet("M Lane");
        publisher = publisherRepository.save(publisher);

        Book book = new Book();
        book.setAuthorId(author.getId());
        book.setPublisherId(publisher.getId());
        book.setTitle("test1");
        book.setIsbn("1111111111111");
        book.setPublishDate(LocalDate.of(2011, 5, 1));
        book.setPrice(new BigDecimal("1.11"));

        book = bookRepository.save(book);

        // Assert...
        List<Book> bookByAuthorId = bookRepository.findByAuthorId(book.getAuthorId());

        assertTrue(bookByAuthorId.contains(book));

    }

}
