package craftcode.workshop.beer.services;

import craftcode.workshop.beer.enums.Country;
import craftcode.workshop.beer.model.Beer;
import craftcode.workshop.beer.model.Brewery;
import craftcode.workshop.beer.repository.BeerRepository;
import craftcode.workshop.beer.repository.BreweryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BreweryServiceTests {

    @InjectMocks
    BreweryService breweryService;

    @Mock
    BreweryRepository breweryRepository;
    @Mock
    BeerRepository beerRepository;

    List<Brewery> breweries;

    @BeforeEach
    void setUp() {
        Brewery brewery = new Brewery();
        brewery.setName("Brewery");
        brewery.setLocation("BELGIUM");
        Brewery brewery2 = new Brewery();
        brewery2.setName("Brewery2");
        brewery2.setLocation("GERMANY");
        Brewery brewery3 = new Brewery();
        brewery3.setName("Brewery3");
        brewery3.setLocation("BELGIUM");

        breweries = List.of(brewery, brewery2, brewery3);
    }

    @Test
    void shouldReturnAllBreweries() {
        when(breweryRepository.findAll()).thenReturn(breweries);

        List<Brewery> foundBreweries = breweryService.getAllBreweries();

        assertThat(foundBreweries)
                .isNotEmpty()
                .hasSize(3);
    }

    @Test
    void shouldReturnABrewery() {
        Brewery brewery1 = new Brewery();
        brewery1.setId(1L);
        brewery1.setName("Brewery1");
        brewery1.setLocation("BELGIUM");

        when(breweryRepository.findById(1L)).thenReturn(Optional.of(brewery1));

        Optional<Brewery> foundBrewery = breweryService.getBreweryById(1L);

        assertThat(foundBrewery).isNotNull();

        String name = foundBrewery.get().getName();
        assertThat(name).isEqualTo("Brewery1");
    }

    @Test
    void shouldSaveABrewery() {
        Brewery brewery1 = new Brewery();
        brewery1.setId(1L);
        brewery1.setName("Brewery1");
        brewery1.setLocation("BELGIUM");

        when(breweryRepository.save(brewery1)).thenReturn(brewery1);

        Brewery savedBrewery = breweryService.saveBrewery(brewery1);
        assertThat(savedBrewery.getName()).isEqualTo(brewery1.getName());
    }

    @Test
    void shouldUpdateABrewery() {
        Brewery existingBrewery = new Brewery();
        existingBrewery.setId(1L);
        existingBrewery.setName("existingBrewery");
        existingBrewery.setLocation("BELGIUM");

        Brewery update = new Brewery();
        update.setId(1L);
        update.setName("updatedBrewery");
        update.setLocation(Country.GERMANY.toString());

        when(breweryRepository.findById(1L)).thenReturn(Optional.of(existingBrewery));
        when(breweryRepository.save(existingBrewery)).thenReturn(existingBrewery);

        Optional<Brewery> updatedBrewery = breweryService.updateBrewery(1L, update);

        assertThat(updatedBrewery).isPresent();
        assertThat(updatedBrewery.get().getName()).isEqualTo("updatedBrewery");

    }

    @Test
    void shouldUnlinkBeerFromSpecificBrewery() {
        Brewery existingBrewery = new Brewery();
        existingBrewery.setId(1L);
        existingBrewery.setName("existingBrewery");
        existingBrewery.setLocation("BELGIUM");

        Beer beer = new Beer();
        beer.setId(1L);
        beer.setName("Grimbergen blond");

        existingBrewery.setBeers(new HashSet<>(Set.of(beer)));

        when(breweryRepository.findById(1L)).thenReturn(Optional.of(existingBrewery));
        when(beerRepository.findById(1L)).thenReturn(Optional.of(beer));
        when(breweryRepository.save(existingBrewery)).thenReturn(existingBrewery);

        boolean result = breweryService.unlinkBeerFromBrewery(1L, 1L);

        assertThat(result).isTrue();
        assertThat(existingBrewery.getBeers()).isEmpty();
    }

    @Test
    void shouldDeleteABrewery() {
        Long breweryId = 1L;

        when(breweryRepository.existsById(breweryId)).thenReturn(true);

        boolean result = breweryService.deleteBrewery(1L);

        verify(breweryRepository).deleteById(breweryId);
        assertThat(result).isTrue();
    }
}
