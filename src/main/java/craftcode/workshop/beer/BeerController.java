package craftcode.workshop.beer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class BeerController {

    private final BeerRepository beerRepository;
    public BeerController(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }



    @GetMapping("/beers")
    private ResponseEntity<Iterable<Beer>> getBeers() {
        List<Beer> beers = beerRepository.findAll();
        if (beers.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(beers);
        }}

    @GetMapping("/beers/{id}")
    private ResponseEntity<Beer> getBeer(@PathVariable Long id){
        Optional<Beer> beer = beerRepository.findById(id);
        return beer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }
}
