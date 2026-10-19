package de.fi.springconsolenapp.translator;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Qualifier("lower")
//@Profile("production")
//@Primary
public class ToLowerTranslator implements  Translator
{

    @Override
    public String translate(final String message) {
        return message.toLowerCase();
    }
}
