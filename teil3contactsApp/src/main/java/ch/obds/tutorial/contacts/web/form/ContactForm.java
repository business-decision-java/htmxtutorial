package ch.obds.tutorial.contacts.web.form;

import ch.obds.tutorial.contacts.web.ContactsController;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@ContactsController.UniqueEmail
public class ContactForm extends Form {

    @NotEmpty(message = "Contact's name cannot be empty.")
    @Size(min = 3, max = 250, message = "Contact's name must be between 3 and 250 characters.")
    private String name = "";

    @NotEmpty(message = "Contact's phone cannot be empty.")
    private String phone = "";

    public ContactForm(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
