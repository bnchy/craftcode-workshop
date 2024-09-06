package craftcode.workshop.beer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import craftcode.workshop.beer.enums.BeerType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Beer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private int alcoholPercentage;

    @Enumerated(EnumType.STRING)
    private BeerType beerType;


    @ManyToOne
    @JoinColumn(name= "brewery_id")
    @JsonIgnoreProperties("beers")
    private Brewery brewery;


    @ManyToMany(mappedBy = "beers")
    @JsonIgnoreProperties("beers")
    private Set<Classification> classifications;

}
