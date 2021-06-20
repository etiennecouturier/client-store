package com.etienne.client.store.model.stats;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CountPerDate {
    private Date date;
    private Integer count;
}
