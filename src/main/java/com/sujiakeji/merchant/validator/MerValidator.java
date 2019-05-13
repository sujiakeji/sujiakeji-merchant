package com.sujiakeji.merchant.validator;

import com.sujiakeji.merchant.domain.Merchant;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class MerValidator implements Validator {

    private static final String MOBILE_REGEX = "^1\\d{10}$";

    private static final String EMAIL_REGEX = "^[\\w\\d._-]+@[\\w\\d.-]+\\.[\\w\\d]{2,6}$";

    public boolean supports(Class clazz) {
        return Merchant.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Merchant merchant = (Merchant) target;
        String merName = merchant.getMerName();

        if (!isValidLength(merName, 1, 30)) {
            errors.rejectValue("merName", "merchant.merName.size");
        }
    }

    public boolean isValidLength(String input, Integer minLength, Integer maxLength) {
        return input != null && input.length() > minLength && input.length() < maxLength;
    }

    public boolean isValidRegex(String regex, String input) {
        if (input == null || input.length() == 0) {
            return false;
        }
        return Pattern.compile(regex).matcher(input).matches();
    }

}