package craftcode.workshop.beer.services;

import craftcode.workshop.beer.enums.BeerType;
import craftcode.workshop.beer.model.Beer;
import craftcode.workshop.beer.repository.BeerRepository;
import craftcode.workshop.beer.repository.ClassificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SpringBootTest
class BeerServiceTests {

    @Autowired
    BeerService beerService;

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    ClassificationRepository classificationRepository;

    @BeforeEach
    void setUp() {
        classificationRepository.deleteAll();
        beerRepository.deleteAll();

        Beer beer1 = new Beer();
        beer1.setName("chouffe");
        beer1.setAlcoholPercentage(6);
        beer1.setBeerType(BeerType.ALE);
        Beer beer2 = new Beer();
        beer2.setName("grimbergen");
        beer2.setAlcoholPercentage(6);
        beer2.setBeerType(BeerType.FRUIT);
        Beer beer3 = new Beer();
        beer3.setName("pils");
        beer3.setAlcoholPercentage(10);
        beer3.setBeerType(BeerType.FRUIT);

        beerRepository.saveAll(List.of(beer1, beer2, beer3));
    }

    @Test
    void shouldReturnAllBeers() {
        List<Beer> beers = beerService.getAllBeers();
        assertThat(beers)
                .isNotEmpty()
                .hasSize(3);

        String name = beers.get(0).getName();
        assertThat(name).isEqualTo("chouffe");
    }

    @Test
    void shouldSaveABeer() {
        Beer beer = new Beer();
        beer.setName("Rene Lindemans kriek");
        beer.setBeerType(BeerType.CHERRY);
        beerService.saveBeer(beer);

        List<Beer> newBeers = beerService.getAllBeers();
        int count = newBeers.size();
        assertThat(count).isEqualTo(4);
        String name = newBeers.get(3).getName();
        assertThat(name).isEqualTo("Rene Lindemans kriek");
    }
}