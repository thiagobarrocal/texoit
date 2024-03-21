package com.texo.gra.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@SuperBuilder
@Table(name="movies", schema = "public")
public class Movie extends BaseEntity {
    public static final String TABLE_NAME = "movies";

    @Serial
    private static final long serialVersionUID = 2137607105409362080L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "movie_year")
    private Integer year;

    @Column(name = "movie_title")
    private String title;

    @Column(name = "movie_studios")
    private String studios;

    @Column(name = "movie_producers")
    private String producers;

    @Column(name = "movie_winner")
    private boolean winner;

    public static Movie fromCsv(String line) {
        String[] parts = line.split(";");
        int year = Integer.parseInt(parts[0].trim());
        String title = parts[1].trim();
        String studios = parts[2].trim();
        String producers = parts[3].trim();
        boolean winner = false;
        if (parts.length > 4) {
            String winnerString = parts[4].trim();
            winner = winnerString.equalsIgnoreCase("yes");
        }
        return Movie.builder()
        .year(year)
        .title(title)
        .studios(studios)
        .producers(producers)
        .winner(winner).build();
    }
}
