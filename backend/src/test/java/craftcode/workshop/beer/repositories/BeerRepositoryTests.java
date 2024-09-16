package craftcode.workshop.beer.repositories;

import craftcode.workshop.beer.model.Beer;
import craftcode.workshop.beer.repository.BeerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
@Transactional
class BeerRepositoryTests {

    @Autowired
    private BeerRepository beerRepository;

    @Test
    void shouldFindBeersByBreweryId() {
        List<Beer> foundBeers = beerRepository.findBeersByBreweryId(1L);
        assertThat(foundBeers).hasSize(3);
    }
}
