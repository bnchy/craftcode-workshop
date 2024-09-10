package craftcode.workshop.beer.controllers;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import craftcode.workshop.beer.config.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class BreweryControllerTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnAllBreweries () {
        ResponseEntity<String> response = restTemplate.getForEntity("/breweries", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        assertThat(documentContext).isNotNull();

        int count = documentContext.read("$.length()", Integer.class);
        assertThat(count).isEqualTo(3);

        String name = documentContext.read("$[0].name", String.class);
        assertThat(name).isEqualTo("Brasserie d'Achouffe");
    }

    @Test
    void shouldReturnABrewery() {
        ResponseEntity<String> response = restTemplate.getForEntity("/breweries/2", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        assertThat(documentContext).isNotNull();

        long id = documentContext.read("$.id", Long.class);
        assertThat(id).isEqualTo(2L);

        String name = documentContext.read("$.name", String.class);
        assertThat(name).isEqualTo("Microbrouwerij Grimbergen");
    }

}
