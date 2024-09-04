package craftcode.workshop.beer.model;

import craftcode.workshop.beer.enums.Country;
import craftcode.workshop.beer.enums.FermentationType;
import craftcode.workshop.beer.enums.GrainTypes;
import craftcode.workshop.beer.enums.NamesAndOrigins;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Classification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Country country;
    private GrainTypes usedGrainType;
    private FermentationType fermentationType;
    private NamesAndOrigins namesAndOrigins;

}
