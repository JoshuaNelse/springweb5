package com.josh.spring5webapp.bootstrap;

import com.josh.spring5webapp.domain.Author;
import com.josh.spring5webapp.domain.Book;
import com.josh.spring5webapp.domain.Publisher;
import com.josh.spring5webapp.repositories.AuthorRepository;
import com.josh.spring5webapp.repositories.BookRepository;
import com.josh.spring5webapp.repositories.PublisherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;


    public BootStrapData(AuthorRepository authorRepository,
                         BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher toastPublisher = new Publisher("Toast",
                "123 Toast Street", "Franklin", "Egypt", "331122");

        publisherRepository.save(toastPublisher);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123", toastPublisher);

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "345345345", toastPublisher);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        log.info("Starting in Bootstrap");
        log.info("Number of Books: " + bookRepository.count());

        toastPublisher.getBooks().add(ddd);
        toastPublisher.getBooks().add(noEJB);

        log.info("Publisher Entity: " + publisherRepository.count());
    }
}
