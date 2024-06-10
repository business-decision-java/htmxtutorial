package ch.obds.tutorial.contacts.web;

import ch.obds.tutorial.contacts.repo.Contact;
import ch.obds.tutorial.contacts.repo.ContactRepository;
import ch.obds.tutorial.contacts.web.form.ContactForm;
import ch.obds.tutorial.contacts.web.form.EditContactForm;
import ch.obds.tutorial.contacts.web.form.NewContactForm;
import jakarta.validation.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Controller
@RequestMapping({"/contacts"})
public class ContactsController {

    public static final String EMAIL_ALREADY_EXISTS = "Email already exists.";
    private final ContactRepository contactRepository;

    public ContactsController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping("")
    public ModelAndView contacts(@RequestParam(defaultValue = "") String query,
                                 @RequestParam(defaultValue = "0") Integer page,
                                 @RequestParam(defaultValue = "10") Integer size,
                                 @RequestParam(defaultValue = "email,asc") String[] sort,
                                 ModelMap model) {
        Sort sortOrder = getSort(sort);
        Pageable pageRequest = PageRequest.of(page, size, sortOrder);
        Page<Contact> contactsSet = StringUtils.isNoneEmpty(query) ? this.contactRepository.findByNameContainingIgnoreCase(query, pageRequest) : this.contactRepository.findAll(pageRequest);
        model.addAttribute("contacts", contactsSet);
        model.addAttribute("query", query);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("sort", sort);
        return new ModelAndView("index", model);
    }

    @GetMapping("/{id}")
    public ModelAndView getContact(@PathVariable String id, ModelMap model) {
        Contact contact = this.contactRepository.findById(Long.parseLong(id)).orElse(null);
        model.addAttribute("contact", contact);
        return new ModelAndView("show", model);
    }

    @GetMapping("/new")
    public ModelAndView newContact(ModelMap model) {
        model.addAttribute("contact", new NewContactForm());
        return new ModelAndView("new", model);
    }

    @GetMapping("/{id}/email")
    @ResponseBody
    public String validateEmail(@PathVariable String id, @RequestParam(required = false) String email, ModelMap modelMap) {

        NewContactForm tmpForm = new NewContactForm();
        tmpForm.setEmail(email);

        var bindingResult = new BeanPropertyBindingResult(tmpForm, "contact");
        tmpForm.setBindingResult(bindingResult);

        if (email != null) {
            boolean isExisting = contactRepository.existsByEmailAndIdNot(email, Long.parseLong(id));

            if (isExisting) {
                bindingResult.addError(new FieldError("contact", "email", EMAIL_ALREADY_EXISTS));
            }
        }

        return bindingResult.hasErrors() ? bindingResult.getFieldError().getDefaultMessage() : "";
    }

    @GetMapping({"/{id}/edit"})
    public ModelAndView editContact(@PathVariable String id, ModelMap model) {
        Contact contact = this.contactRepository.findById(Long.parseLong(id)).orElse(null);
        model.addAttribute("contact", contact);
        return new ModelAndView("edit", model);
    }

    @PostMapping({"/new"})
    public String handleNewContact(@Valid @ModelAttribute("contact") NewContactForm newContact,
                                   BindingResult bindingResult,
                                   ModelMap model,
                                   RedirectAttributes redirectAttributes
    ) {

        if (bindingResult.hasErrors()) {
            return "new";
        }

        this.contactRepository.save(new Contact(newContact.getName(), newContact.getEmail(), newContact.getPhone()));
        redirectAttributes.addFlashAttribute("message", "Contact created");
        return "redirect:/contacts";
    }

    @PostMapping({"/{id}/edit"})
    public String handleEditContact(@Valid @ModelAttribute("contact") EditContactForm editContact,
                                    BindingResult bindingResult,
                                    ModelMap model,
                                    RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "edit";
        }
        this.contactRepository.save(new Contact(Long.parseLong(editContact.getId()), editContact.getName(), editContact.getEmail(), editContact.getPhone()));
        redirectAttributes.addFlashAttribute("message", "Contact saved");
        return "redirect:/contacts/%s".formatted(editContact.getId());
    }

    @DeleteMapping({"/{id}"})
    public ModelAndView handleDelete(@PathVariable String id, ModelMap model, RedirectAttributes redirectAttributes) {
        this.contactRepository.deleteById(Long.parseLong(id));
        redirectAttributes.addFlashAttribute("message", "Contact deleted");
        return new ModelAndView("redirect:/contacts", HttpStatus.SEE_OTHER);
    }

    @Constraint(validatedBy = UniqueEmailValidator.class)
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE})
    public @interface UniqueEmail {
        String message() default EMAIL_ALREADY_EXISTS;
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

    class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, ContactForm> {

        private final ContactRepository repository;

        UniqueEmailValidator(ContactRepository repository) {

            this.repository = repository;
        }

        @Override
        public boolean isValid(ContactForm contactForm, ConstraintValidatorContext context) {

            boolean valid = true;
            if (contactForm instanceof NewContactForm newContactForm) {
                valid = !repository.existsByEmail(newContactForm.getEmail());
            }
            if (contactForm instanceof EditContactForm editContactForm) {
                valid =  !repository.existsByEmailAndIdNot(editContactForm.getEmail(), Long.parseLong(editContactForm.getId()));
            }
            if (!valid) {
                // set error for specific property "email", otherwise the error would be on the contact itself
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(EMAIL_ALREADY_EXISTS)
                        .addPropertyNode("email")
                        .addConstraintViolation();

            }
            return valid;
        }
    }

    private Sort getSort(String[] sort) {
        String sortField = sort[0];
        String sortDirection = sort[1];
        Sort.Direction direction = (sortDirection.equals("desc")) ? Sort.Direction.DESC : Sort.Direction.ASC;
        return Sort.by(new Sort.Order(direction, sortField));
    }
}
