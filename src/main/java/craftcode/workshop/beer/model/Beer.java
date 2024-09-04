package craftcode.workshop.beer.model;

import craftcode.workshop.beer.enums.BeerType;
import jakarta.persistence.*;
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


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name= "brewery_id")
    private Brewery brewery;

}
