package craftcode.workshop.beer;

import craftcode.workshop.beer.enums.BeerType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Beer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private int alcoholPercentage;

    @Enumerated(EnumType.STRING)
    private BeerType beerType;

    private String manufacturedAt;

}
