package craftcode.workshop.beer.controllers;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import craftcode.workshop.beer.enums.BeerType;
import craftcode.workshop.beer.enums.Country;
import craftcode.workshop.beer.model.Beer;
import craftcode.workshop.beer.repository.BeerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BeerControllerTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    BeerRepository beerRepository;

    @Test
    void shouldReturnAllBeers() {
        ResponseEntity<String> response = restTemplate.getForEntity("/beers", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        assertThat(documentContext.read("$.length()", Integer.class)).isEqualTo(3);
        assertThat(documentContext.read("$[0].name", String.class)).isEqualTo("chouffe");

    }

    @Test
    void shouldReturnABeer() {
        ResponseEntity<String> response = restTemplate.getForEntity("/beers/1",String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext context = JsonPath.parse(response.getBody());
        assertThat(context).isNotNull();

        long id = context.read("$.id", Long.class);
        String name = context.read("$.name", String.class);

        assertThat(id).isEqualTo(1L);
        assertThat(name).isEqualTo("chouffe");
    }
    @Test
    void shouldSaveABeer() {
        Beer beer = new Beer();
        beer.setName("Rene Lindemans kriek");
        beer.setAlcoholPercentage(4);
        beer.setBeerType(BeerType.CHERRY);

        ResponseEntity<Void> createResponse = restTemplate.postForEntity("/beers", beer, Void.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        Optional<Beer> savedBeer = beerRepository.findById(4L);
        assertThat(savedBeer).isNotNull();

        Long id = savedBeer.get().getId();
        String name = savedBeer.get().getName();

        assertThat(id).isEqualTo(4L);
        assertThat(name).isEqualTo("Rene Lindemans kriek");


        beerRepository.deleteById(4L);
    }
    
    @Test
    void shouldReturnAClassification() {
        ResponseEntity<String> response = restTemplate.getForEntity("/classifications/1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        assertThat(documentContext).isNotNull();

        long id = documentContext.read("$.id", Long.class);
        assertThat(id).isEqualTo(1L);

        String countryStr = documentContext.read("$.country", String.class);
        Country country = Country.valueOf(countryStr);
        assertThat(country).isEqualTo(Country.BELGIUM);
    }

    @Test
    void shouldReturnAllClassifications() {
        ResponseEntity<String> response = restTemplate.getForEntity("/classifications", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        assertThat(documentContext).isNotNull();


        int count = documentContext.read("$.length()", Integer.class);
        assertThat(count).isEqualTo(2);

        String countrystr = documentContext.read("$[0].country", String.class);
        Country country = Country.valueOf(countrystr);
        assertThat(country).isEqualTo(Country.BELGIUM);
    }


}
