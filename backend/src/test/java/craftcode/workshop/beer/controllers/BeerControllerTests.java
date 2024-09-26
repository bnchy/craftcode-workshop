package craftcode.workshop.beer.controllers;

import craftcode.workshop.beer.controller.BeerController;
import craftcode.workshop.beer.dtos.BeerDTO;
import craftcode.workshop.beer.enums.BeerType;
import craftcode.workshop.beer.mappers.BeerMapper;
import craftcode.workshop.beer.model.Beer;
import craftcode.workshop.beer.services.BeerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.data.domain.Page;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BeerControllerTests {

    @Mock
    BeerService beerService;

    @Mock
    BeerMapper beerMapper;

    @InjectMocks
    BeerController beerController;

    List<Beer> beers;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        Beer beer1 = new Beer();
        beer1.setName("Chouffe");
        beer1.setAlcoholPercentage(6);
        beer1.setBeerType(BeerType.ALE);
        Beer beer2 = new Beer();
        beer2.setName("Grimbergen");
        beer2.setAlcoholPercentage(6);
        beer2.setBeerType(BeerType.FRUIT);
        Beer beer3 = new Beer();
        beer3.setName("Pils");
        beer3.setAlcoholPercentage(10);
        beer3.setBeerType(BeerType.FRUIT);

        beers = List.of(beer1, beer2, beer3);
    }

    @Test
    void shouldReturnAllBeers() {
        int pageNr = 1;
        int pageSize = 12;
        Pageable pageable = PageRequest.of(0, pageSize);
        Page<Beer> pagedBeers = new PageImpl<>(beers, pageable, beers.size());

        when(beerService.getAllBeers(pageable)).thenReturn(pagedBeers);

        ResponseEntity<Page<Beer>> response = beerController.getBeers(pageNr,pageSize);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getContent()).hasSize(beers.size());
    }

    @Test
    void shouldReturnABeer() {
        Beer beer = beers.get(0);

        when(beerService.getBeerById(1L)).thenReturn(Optional.of(beers.get(0)));


        ResponseEntity<Beer> response = beerController.getBeer(1L);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo(beers.get(0).getName());
    }

    @Test
    void shouldReturnBeersByBreweryId() {
        when(beerService.getBeersByBreweryId(1L)).thenReturn(beers);
        ResponseEntity<List<Beer>> foundBeers = beerController.getBeersByBreweryId(1L);

        assertThat(foundBeers.getBody()).isNotNull();
        assertThat(foundBeers.getBody().size()).isEqualTo(beers.size());
    }

    @Test
    void shouldSaveABeer() {
        Beer newBeer = new Beer();
        newBeer.setName("Duvel");
        newBeer.setAlcoholPercentage(8);
        newBeer.setBeerType(BeerType.ALCOHOL_FREE);

        Beer savedBeer = new Beer();
        savedBeer.setId(1L);
        savedBeer.setName("Duvel");
        savedBeer.setAlcoholPercentage(8);
        savedBeer.setBeerType(BeerType.ALCOHOL_FREE);

        when(beerService.saveBeer(newBeer)).thenReturn(savedBeer);

        UriComponentsBuilder ucb = UriComponentsBuilder.fromPath("");
        ResponseEntity<Beer> response = beerController.addBeer(newBeer, null, ucb);


        assertThat(response.getHeaders().getLocation()).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getHeaders().getLocation().toString()).isEqualTo("/beers/1");
    }


    @Test
    void shouldUpdateABeer(){
        Beer existingBeer = new Beer();
        existingBeer.setId(1L);
        existingBeer.setName("Chouffe");
        existingBeer.setAlcoholPercentage(8);
        existingBeer.setBeerType(BeerType.ALE);

        Beer updatedBeer = new Beer();
        updatedBeer.setName("Duvel");
        updatedBeer.setAlcoholPercentage(4);
        updatedBeer.setBeerType(BeerType.ALCOHOL_FREE);

        when(beerService.updateBeer(1L, updatedBeer)).thenReturn(Optional.of(updatedBeer));

        ResponseEntity<Beer> response = beerController.updateBeer(1L,  updatedBeer);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo(updatedBeer.getName());

    }

    @Test
    void shouldDeleteABeer() {
        Beer existingBeer = new Beer();
        existingBeer.setId(1L);
        existingBeer.setName("Chouffe");
        existingBeer.setAlcoholPercentage(8);

        when(beerService.deleteBeer(1L)).thenReturn(true);

        ResponseEntity<Void> response = beerController.deleteBeer(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

}

