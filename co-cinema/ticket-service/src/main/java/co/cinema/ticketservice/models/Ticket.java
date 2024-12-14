package co.cinema.ticketservice.models;


import co.cinema.ticketservice.classe.Film;
import co.cinema.ticketservice.classe.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer filmId;

    private Integer personId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate bookDate;

    @DateTimeFormat(pattern = "HH:mm")
    private Time bookTime;

    @Transient
    private Film film;

    @Transient
    private Person person;
}
