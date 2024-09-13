package craftcode.workshop.beer.controllers;

import craftcode.workshop.beer.controller.BreweryController;
import craftcode.workshop.beer.enums.Country;
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

import java.util.List;
import java.util.Optional;

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
        MockitoAnnotations.openMocks(this);

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

}
