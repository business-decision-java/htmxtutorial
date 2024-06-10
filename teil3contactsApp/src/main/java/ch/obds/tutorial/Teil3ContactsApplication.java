package ch.obds.tutorial;

import ch.obds.tutorial.contacts.repo.Contact;
import ch.obds.tutorial.contacts.repo.ContactRepository;
import ch.obds.tutorial.contacts.repo.DataFaker;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Teil3ContactsApplication {

    private static final int NUMBER_OF_CONTACTS = 100;
    private static final Logger log = LoggerFactory.getLogger(Teil3ContactsApplication.class);

    @Autowired
    private DataFaker dataFaker;

    public static void main(String[] args) {
        SpringApplication.run(Teil3ContactsApplication.class, args);
    }

    @PostConstruct
    public void loadData() {
        log.info("Loading demo data...");
        dataFaker.initialDataLoad(NUMBER_OF_CONTACTS);
        log.info("%d contacts have been generated".formatted(NUMBER_OF_CONTACTS));
    }
}
