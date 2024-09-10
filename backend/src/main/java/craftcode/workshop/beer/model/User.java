package craftcode.workshop.beer.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "beeruser")
public class User extends BaseUser{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
