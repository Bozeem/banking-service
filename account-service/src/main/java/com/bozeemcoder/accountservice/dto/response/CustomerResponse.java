package com.bozeemcoder.accountservice.dto.response;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerResponse {
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    String address;
    Set<AccountResponse> accounts;
}
