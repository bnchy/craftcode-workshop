package craftcode.workshop.beer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import craftcode.workshop.beer.enums.Country;
import craftcode.workshop.beer.enums.FermentationType;
import craftcode.workshop.beer.enums.GrainTypes;
import craftcode.workshop.beer.enums.NamesAndOrigins;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Classification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Country country;
    @Enumerated(EnumType.STRING)
    private GrainTypes usedGrainType;
    @Enumerated(EnumType.STRING)
    private FermentationType fermentationType;
    @Enumerated(EnumType.STRING)
    private NamesAndOrigins namesAndOrigins;

    @ManyToMany
    @JoinTable(
            name= "classification_beer",
            joinColumns = @JoinColumn(name = "classification_id"),
            inverseJoinColumns = @JoinColumn(name = "beer_id")
    )
    @JsonIgnoreProperties("classifications")
    private Set<Beer> beers = new HashSet<>();
}
