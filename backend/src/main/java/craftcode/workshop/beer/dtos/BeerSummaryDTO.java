package craftcode.workshop.beer.dtos;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeerSummaryDTO {
    private long id;
    private String name;
    private int alcoholPercentage;
}
