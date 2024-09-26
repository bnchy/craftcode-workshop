package craftcode.workshop.beer.dtos;

import craftcode.workshop.beer.enums.BeerType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeerDTO {
    private long id;
    private String name;
    private int alcoholPercentage;
    private BeerType beerType;

    private BreweryDTO brewery;
    private ClassificationDTO classification;



}
