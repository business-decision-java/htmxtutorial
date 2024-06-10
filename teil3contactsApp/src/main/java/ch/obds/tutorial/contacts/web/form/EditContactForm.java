package ch.obds.tutorial.contacts.web.form;

import jakarta.validation.constraints.NotEmpty;

public class EditContactForm extends ContactForm {

    @NotEmpty(message = "Contact's id cannot be empty.")
    private String id;

    @NotEmpty(message = "Contact's email cannot be empty.")
    private String email;

    public EditContactForm(String name, String id, String email) {
        super(name);
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
