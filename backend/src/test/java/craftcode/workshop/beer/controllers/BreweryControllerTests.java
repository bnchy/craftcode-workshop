package craftcode.workshop.beer.controllers;

import craftcode.workshop.beer.controller.BreweryController;
import craftcode.workshop.beer.enums.Country;
import craftcode.workshop.beer.model.Beer;
import craftcode.workshop.beer.model.Brewery;
import craftcode.workshop.beer.services.BreweryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BreweryControllerTests {

    @Mock
    private BreweryService breweryService;

    @InjectMocks
    private BreweryController breweryController;

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
    void shouldReturnAllBreweries () {

        when(breweryService.getAllBreweries()).thenReturn(breweries);

        ResponseEntity<List<Brewery>> response = breweryController.getBreweries();

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    void shouldReturnABreweryById() {

        when(breweryService.getBreweryById(1L)).thenReturn(Optional.of(breweries.get(0)));

        ResponseEntity<Brewery> response = breweryController.getBreweryById(1L);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo(breweries.get(0).getName());
    }

    @Test
    void shouldSaveBrewery() {
        Brewery brewery = new Brewery();
        brewery.setId(1);
        brewery.setName("Brewery");
        brewery.setLocation("BELGIUM");

        when(breweryService.saveBrewery(brewery)).thenReturn(brewery);

        UriComponentsBuilder ucb = UriComponentsBuilder.fromPath("");
        ResponseEntity<Brewery> response = breweryController.saveBrewery(brewery, null, ucb);

        assertThat(response.getHeaders().getLocation()).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getHeaders().getLocation().toString()).isEqualTo("/breweries/1");
    }

    @Test
    void shouldUpdateABrewery() {
        Brewery existingBrewery = new Brewery();
        existingBrewery.setName("Brewery");
        existingBrewery.setLocation("BELGIUM");

        Brewery updatedBrewery = new Brewery();
        updatedBrewery.setName("UpdatedBrewery");
        updatedBrewery.setLocation(Country.GERMANY.toString());

        when(breweryService.updateBrewery(1L,updatedBrewery)).thenReturn(Optional.of(updatedBrewery));

        ResponseEntity<Brewery> response = breweryController.updateBrewery(1L, updatedBrewery);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo(updatedBrewery.getName());
    }
    @Test
    void shouldUnlinkABeerFromBrewery() {
        Brewery existingBrewery = new Brewery();
        existingBrewery.setId(1L);
        existingBrewery.setName("Brewery");
        existingBrewery.setLocation("BELGIUM");

        Beer beer = new Beer();
        beer.setId(1L);
        beer.setName("Grimbergen blond");

        existingBrewery.setBeers(new HashSet<>(Set.of(beer)));

        when(breweryService.unlinkBeerFromBrewery(1L, 1L)).thenReturn(true);

        ResponseEntity<Void> response = breweryController.unlinkBeer(1L, 1L);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void shouldDeleteABrewery() {
        Brewery existingBrewery = new Brewery();
        existingBrewery.setId(1L);
        existingBrewery.setName("Brewery");
        existingBrewery.setLocation("BELGIUM");

        when(breweryService.deleteBrewery(1L)).thenReturn(true);

        ResponseEntity<Void> result = breweryController.deleteBreweryById(1L);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}
