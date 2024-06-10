package ch.obds.tutorial.contacts.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Long>, PagingAndSortingRepository<Contact, Long> {

    List<Contact> findByNameContainingIgnoreCase(String name);

    Page<Contact> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Contact> findAll(Pageable pageable);

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