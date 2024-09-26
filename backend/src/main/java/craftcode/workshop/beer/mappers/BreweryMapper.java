package craftcode.workshop.beer.mappers;

import craftcode.workshop.beer.dtos.BreweryDTO;
import craftcode.workshop.beer.model.Brewery;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BreweryMapper {

    BreweryDTO toBreweryDTO(Brewery brewery);
    Brewery toEntity(BreweryDTO breweryDTO);
}
