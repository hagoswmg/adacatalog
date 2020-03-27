package com.wmg.adacatalog.model;

import lombok.Data;

@Data
public class Catalog {
    private String gpid;
    private String grid;
    private String artist;
    private String title;
    private String ddexReleaseType;
    private String formatCode;
    private String formatDescription;
    private String majorLabel;
    private String marketingLabel;
    private String presentationLabel;
    private String explicitRating;
}
