package com.imooc.uaa.validation;

import com.imooc.uaa.domain.dto.UserDto;
import com.imooc.uaa.validation.annotation.PasswordMatches;
import lombok.val;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserDto> {

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) { }

    @Override
    public boolean isValid(final UserDto obj, final ConstraintValidatorContext context) {
        UserDto user = (UserDto) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
