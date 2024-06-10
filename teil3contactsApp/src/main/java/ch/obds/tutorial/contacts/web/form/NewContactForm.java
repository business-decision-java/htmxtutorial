package ch.obds.tutorial.contacts.web.form;

import jakarta.validation.constraints.NotEmpty;

public class NewContactForm extends ContactForm {

    @NotEmpty(message = "Contact's email cannot be empty.")
    private String email;

    public NewContactForm(String name, String email) {
        super(name);
        this.email = email;
    }

    public NewContactForm() {
        super(null);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
