package craftcode.workshop.beer.services;

import craftcode.workshop.beer.model.Brewery;
import craftcode.workshop.beer.repository.BreweryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BreweryServiceTests {

    @InjectMocks
    BreweryService breweryService;

    @Mock
    BreweryRepository breweryRepository;

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
}
