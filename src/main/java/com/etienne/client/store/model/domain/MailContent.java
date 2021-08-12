package com.etienne.client.store.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MailContent {
    private String visitId;
    private String title;
    private String body;
}
