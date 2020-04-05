package com.wmg.adacatalog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenplayProduct {

    @Id
    @Column(name = "SET_ID")
    private Long setId;

    @Column(name = "SET_NAME")
    private String setName;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "PROCESSED_DATE")
    private Date processedDate;

    @Column(name = "LAST_UPDATE_DATE")
    private Date lastUpdateDate;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "DIRECTORY")
    private String directory;

    @Column(name = "COMPANY")
    private String company;

    @Column(name = "COMMENTS")
    private String comments;

}
