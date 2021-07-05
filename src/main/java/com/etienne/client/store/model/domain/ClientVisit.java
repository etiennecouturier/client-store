package com.etienne.client.store.model.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * client with a single visit
 */

@Data
@NoArgsConstructor
public class ClientVisit {

    private String id;
    private String name;
    private String tel;
    private Visit visit;

}
