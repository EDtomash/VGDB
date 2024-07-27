package vgdb.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VideogameModel {
    private Integer id;
    private String name;
    private String releaseDate;
    private Integer reviewScore;
    private String category;
    private String rating;
}
