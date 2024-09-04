package craftcode.workshop.beer;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BeerApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

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

}
