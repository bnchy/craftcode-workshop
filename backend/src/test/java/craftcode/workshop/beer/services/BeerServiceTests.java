package craftcode.workshop.beer.services;

import craftcode.workshop.beer.enums.BeerType;
import craftcode.workshop.beer.model.Beer;
import craftcode.workshop.beer.repository.BeerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class BeerServiceTests {

    @InjectMocks
    BeerService beerService;

    @Mock
    BeerRepository beerRepository;

    List<Beer> beers;

    @BeforeEach
    void setUp() {
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
    void shouldReturnABeer() {
        Beer beer = new Beer();
        beer.setId(1L);
        beer.setName("Chouffe");

        when(beerRepository.findById(1L)).thenReturn(Optional.of(beer));

        Optional<Beer> foundBeer = beerService.getBeerById(1L);
        assertThat(foundBeer).isPresent();
        assertThat(foundBeer.get().getName()).isEqualTo("Chouffe");
    }

    @Test
    void shouldReturnAllBeers() {
        when(beerRepository.findAll()).thenReturn(beers);
        List<Beer> foundBeers = beerService.getAllBeers();

        assertThat(foundBeers)
                .isNotEmpty()
                .hasSize(3);

        String name1 = foundBeers.get(0).getName();
        assertThat(name1).isEqualTo("Chouffe");
    }

    @Test
    void shouldReturnBeersByBreweryId(){
        when(beerRepository.findBeersByBreweryId(1L)).thenReturn(beers);
        
        List<Beer> foundBeers = beerService.getBeersByBreweryId(1L);

        assertThat(foundBeers.size()).isEqualTo(3);
    }

    @Test
    void shouldSaveABeer() {
        Beer beer1 = new Beer();
        beer1.setName("Grimbergen");
        beer1.setAlcoholPercentage(6);
        beer1.setBeerType(BeerType.ALE);

        when(beerRepository.save(beer1)).thenReturn(beer1);

        Beer savedBeer = beerService.saveBeer(beer1);

        assertThat(savedBeer.getName()).isEqualTo(beer1.getName());
    }

    @Test
    void shouldUpdateABeer() {
        Beer exisitingBeer = new Beer();
        exisitingBeer.setId(1L);
        exisitingBeer.setName("Chouffe");
        exisitingBeer.setAlcoholPercentage(8);
        exisitingBeer.setBeerType(BeerType.ALE);

        Beer update = new Beer();
        update.setName("Grimbergen");
        update.setAlcoholPercentage(6);
        update.setBeerType(BeerType.FRUIT);

        when(beerRepository.findById(1L)).thenReturn(Optional.of(exisitingBeer));
        when(beerRepository.save(exisitingBeer)).thenReturn(exisitingBeer);

        Optional<Beer> updatedBeer = beerService.updateBeer(1L, update);

        assertThat(updatedBeer).isPresent();
        assertThat(updatedBeer.get().getName()).isEqualTo("Grimbergen");
    }

    @Test
    void shouldDeleteABeer() {
        Long beerId = 1L;

        when(beerRepository.existsById(beerId)).thenReturn(true);

        boolean result = beerService.deleteBeer(beerId);

        verify(beerRepository).deleteById(beerId);
        assertTrue(result);
    }
}