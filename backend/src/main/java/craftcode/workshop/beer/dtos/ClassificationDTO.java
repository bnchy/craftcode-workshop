package craftcode.workshop.beer.dtos;

import craftcode.workshop.beer.enums.Country;
import craftcode.workshop.beer.enums.FermentationType;
import craftcode.workshop.beer.enums.GrainTypes;
import craftcode.workshop.beer.enums.NamesAndOrigins;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ClassificationDTO {
    private long id;
    private Country country;
    private GrainTypes grainType;
    private FermentationType fermentationType;
    private NamesAndOrigins namesAndOrigins;
}
