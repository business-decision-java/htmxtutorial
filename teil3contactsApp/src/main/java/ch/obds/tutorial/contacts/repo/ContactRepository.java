package ch.obds.tutorial.contacts.repo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ContactRepository extends CrudRepository<Contact, Long> {

    List<Contact> findByName(String name);

    List<Contact> findByNameContainingIgnoreCase(String name);

    boolean existsByEmail(String email);

    /**
     * Checks if there are contacts with a given email, excluding a specific entry
     *
     * @param email the mail to check
     * @param id    the entry to exclude
     * @return true, if such an entry exists
     */
    boolean existsByEmailAndIdNot(String email, Long id);
}