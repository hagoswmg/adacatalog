package com.wmg.adacatalog.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
import java.util.Date;

@Data
@AllArgsConstructor
@MappedSuperclass
@SqlResultSetMapping(name = "Catalog", classes = @ConstructorResult(targetClass = Catalog.class,
        columns = {
                @ColumnResult(name = "gpid", type = String.class),
                @ColumnResult(name = "artist", type = String.class),
                @ColumnResult(name = "title", type = String.class),
                @ColumnResult(name = "wea_Labelcode", type = String.class),
                @ColumnResult(name = "marketing_label", type = String.class),
                @ColumnResult(name = "presentation_label", type = String.class),
                @ColumnResult(name = "genre", type = String.class),
                @ColumnResult(name = "format_code", type = String.class),
                @ColumnResult(name = "media_code", type = String.class),
                @ColumnResult(name = "pa_flag", type = String.class),
                @ColumnResult(name = "street_date", type = Date.class),
                @ColumnResult(name = "internal_comments", type = String.class)}))
public class Catalog {
    private String gpid;

    private String artist;

    private String title;

    private String wea_labelcode;

    private String marketing_label;

    private String presentation_label;

    private String genre;

    private String format_code;

    private String media_code;

    private String pa_flag;

    private Date street_date;

    private String internal_comments;

}
