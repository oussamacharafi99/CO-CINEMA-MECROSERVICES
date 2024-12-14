package co.cinema.ticketservice.classe;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalDate;

@Getter
@Setter
public class Film {

    private Integer id;

    private String name;

    private String description;

    private Time movieDuration;

    private LocalDate movieDate;

    private Time movieTime;

    private Integer price;

    private Integer seatsNumber;
}