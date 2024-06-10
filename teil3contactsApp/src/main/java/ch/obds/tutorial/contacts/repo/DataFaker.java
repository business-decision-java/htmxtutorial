package ch.obds.tutorial.contacts.repo;


import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class DataFaker {

    private final ContactRepository contactRepository;
    private Faker faker = new Faker(new Locale("de-CH"));

    public DataFaker(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void initialDataLoad(int numberOfContacts) {
        for (int i=0; i<numberOfContacts; i++) {
            String name = faker.name().name();
            String email = faker.internet().emailAddress(name.toLowerCase().replace(" ", ".").replace("-", "."));
            String phone = faker.phoneNumber().phoneNumber();

            Contact contact = new Contact(name, email, phone);
            contactRepository.save(contact);
        }
    }

}
