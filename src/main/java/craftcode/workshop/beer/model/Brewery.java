package craftcode.workshop.beer.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Brewery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String location;

    @OneToMany(fetch = FetchType.EAGER ,cascade = CascadeType.ALL, mappedBy = "brewery")
    @Fetch(FetchMode.SELECT)
    private Set<Beer> manufacturedBeers;
}
