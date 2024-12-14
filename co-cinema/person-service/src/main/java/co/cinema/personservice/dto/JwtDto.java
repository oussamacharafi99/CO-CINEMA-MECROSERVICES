package co.cinema.personservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JwtDto {
    private Integer personId;
    private String token;
}
