package com.iqbusiness.onlineregistration.domain;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
@Builder
public class Person {
    String fullName;
    @Pattern(regexp = "^((\\d{2}((0[13578]|1[02])(0[1-9]|[12]\\d|3[01])|(0[13456789]|1[012])(0[1-9]|[12]\\d|30)|02(0[1-9]|1\\d|2[0-8])))|([02468][048]|[13579][26])0229)(\\d{4})([01])(\\d{2})$")
    String idNumber;
    @Pattern(regexp = "^\\+27[0-9]{9}$")
    String telephoneNumber;
}
