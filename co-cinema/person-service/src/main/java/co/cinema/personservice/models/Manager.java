package co.cinema.personservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("MANAGER")
public class Manager extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}