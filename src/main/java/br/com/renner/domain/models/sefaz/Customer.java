package br.com.renner.domain.models.sefaz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    private String id;
    private String name;
    private String document;
    private String documentType;
    private String personType;
    private String address;
    private String addressNumber;
    private String addressComplement;
    private String district;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String ibgeCode;
    private String phoneNumber;
    private String email;
}
