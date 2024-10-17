package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private Integer id;
    private String title;
    private Integer year;
    private String genre;

    public Movie(String title, Integer year, String genre) {
        this.title = title;
        this.genre = genre;
        this.year = year;
    }
}
