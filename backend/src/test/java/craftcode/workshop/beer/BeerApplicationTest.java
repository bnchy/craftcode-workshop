package craftcode.workshop.beer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BeerApplicationTest {
    @Test
    void contextLoads() {
        assertThat(true).isTrue();
    }
    @Test
    void main() {
        BeerApplication.main(new String[] {});
    }


}
