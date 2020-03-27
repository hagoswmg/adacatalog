package com.wmg.adacatalog.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class RecentSet {
    private String confirmationCode;
    private String title;
    private String status;
    private Date creationDate;
}
