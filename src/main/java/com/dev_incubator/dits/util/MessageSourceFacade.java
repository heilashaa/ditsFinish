package com.dev_incubator.dits.util;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Locale;

@Component
@AllArgsConstructor
public class MessageSourceFacade {

    private final MessageSource messageSource;

    public String getMessage(String code) {
        try {
            return messageSource.getMessage(code, Collections.emptyList().toArray(), Locale.getDefault());
        } catch (NoSuchMessageException e) {
            System.out.println(e.getMessage());
            return code;
        }
    }
}
