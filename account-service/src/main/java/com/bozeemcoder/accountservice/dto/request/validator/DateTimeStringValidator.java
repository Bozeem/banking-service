package com.bozeemcoder.accountservice.dto.request.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeStringValidator implements ConstraintValidator<DateTimeString, String> {
  private static final DateTimeFormatter FORMATTER =
      DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[.SSSSSS]X");

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return isValidDateTime(value);
  }

  private static boolean isValidDateTime(String dateTimeString) {
    try {
      ZonedDateTime.parse(dateTimeString, FORMATTER);
      return true;
    } catch (DateTimeParseException e) {
      return false;
    }
  }
}
