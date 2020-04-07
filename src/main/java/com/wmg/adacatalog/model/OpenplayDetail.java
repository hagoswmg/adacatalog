package com.wmg.adacatalog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenplayDetail {

    @Id
    @Column(name = "DETAIL_ID")
    private long detailId;

    @Column(name = "SET_ID")
    private long setId;

    @Column(name = "IDENTIFIER")
    private String identifier;

    @Column(name = "DEAL_SENT")
    private String dealSent;

    @Column(name = "PMI_SENT")
    private String pmiSent;

    @Column(name = "COVERART_SENT")
    private String coverArtSent;

    @Column(name = "AUDIO_SENT")
    private String audioSent;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "PMI_BATCH_ID")
    private String pmiBatchId;

    @Column(name = "DEAL_BATCH_ID")
    private String dealBatchId;

}
