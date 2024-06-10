package ch.obds.tutorial.contacts.web.form;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

import java.util.Collections;
import java.util.List;

public abstract class Form {

    private BindingResult bindingResult;

    public final BindingResult getBindingResult() {
        return this.bindingResult;
    }

    public final void setBindingResult(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public final boolean getHasErrors() {
        return this.bindingResult != null && this.bindingResult.hasErrors();
    }

    public final List<String> getErrors() {
        return this.bindingResult != null ? this.bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList() : Collections.emptyList();
    }
}
