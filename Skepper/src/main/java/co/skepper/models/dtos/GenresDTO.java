package co.skepper.models.dtos;

import co.skepper.enums.Genre;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class GenresDTO {

    private Set<Genre> genres;

}
