package craftcode.workshop.beer.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import craftcode.workshop.beer.enums.BeerType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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



    @ManyToOne
    @JoinColumn(name = "classification_id")  // Renamed the column to reflect a single classification
    @JsonIgnoreProperties("beers")
    private Classification classification;

    private int likes;

    private int dislikes;


     public int incrementLikes() {
        return ++likes;
    }

    public int decrementLikes() {
        return --likes;
    }

    public int incrementDislikes() {
        return ++dislikes;
    }

    public int decrementDislikes() {
        return --dislikes;
    }


}
