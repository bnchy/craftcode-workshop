package craftcode.workshop.beer.model;

import jakarta.persistence.*;
import lombok.*;


import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Brewery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String location;

    @OneToMany(mappedBy = "brewery", cascade = CascadeType.ALL )
    private Set<Beer> beers = new HashSet<>();
}
