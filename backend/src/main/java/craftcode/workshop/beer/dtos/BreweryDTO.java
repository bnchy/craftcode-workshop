package craftcode.workshop.beer.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class BreweryDTO {
    private long id;
    private String name;
    private String location;

    private Set<BeerSummaryDTO> beers;
}
