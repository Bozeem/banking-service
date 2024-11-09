package com.bozeemcoder.accountservice.language;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

/** The type Resource language iml. */
@Slf4j
@Service
@RequiredArgsConstructor
public class ResourceLanguageIml implements ResourceLanguage {

  private final MessageSource messageSource;

  @Override
  public String get(String key) {
    try {
      return messageSource.getMessage(key, new Object[0], new Locale("en"));
    } catch (Exception exception) {
      log.debug("Exception Occurred with at ResourceLanguageIml.get with key: " + key, exception);
      return key;
    }
  }

  @Override
  public String get(String key, Object... objects) {
    try {
      return messageSource.getMessage(key, objects, new Locale("en"));
    } catch (Exception exception) {
      log.debug("Exception Occurred with at ResourceLanguageIml.get with key: " + key, exception);
      return key;
    }
  }
}
