package com.etienne.client.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Address {
    private String country;
    private Integer zip;
    private String county;
    private String city;
    private String street;
    private Integer houseNumber;
}
