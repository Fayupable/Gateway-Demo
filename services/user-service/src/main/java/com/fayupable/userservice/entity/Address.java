package com.fayupable.userservice.entity;

import jakarta.persistence.Embeddable;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
@Embeddable
public class Address {
    private String street;
    private String houseNumber;
    private String zipCode;
}
