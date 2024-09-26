package craftcode.workshop.beer.mappers;

import craftcode.workshop.beer.dtos.BeerDTO;
import craftcode.workshop.beer.dtos.BeerSummaryDTO;
import craftcode.workshop.beer.model.Beer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BeerMapper {
    BeerDTO toBeerDTO(Beer beer);
    Beer toEntity(BeerDTO beerDTO);
    BeerSummaryDTO toBeerSummaryDTO(Beer beer);
}
