package com.etienne.client.store.model.stats;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CountPerAge {
    private String range;
    private Integer count;
}
