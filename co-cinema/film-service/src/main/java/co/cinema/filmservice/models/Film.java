package co.cinema.filmservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    @DateTimeFormat(pattern = "HH:mm")
    private Time movieDuration;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate movieDate;

    @DateTimeFormat(pattern = "HH:mm")
    private Time movieTime;

    private Integer price;

    private Integer seatsNumber;

}
