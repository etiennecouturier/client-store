package com.etienne.client.store.model.stats;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CountPerSex {
    private String sex;
    private Integer count;
}
