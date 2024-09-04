package craftcode.workshop.beer.controller;

import craftcode.workshop.beer.model.Beer;
import craftcode.workshop.beer.repository.BeerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/beers")
public class BeerController {

    private final BeerRepository beerRepository;
    public BeerController(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }



    @GetMapping("/")
    public ResponseEntity<List<Beer>> getBeers() {
        List<Beer> beers = beerRepository.findAll();
        if (beers.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(beers);
        }}

    @GetMapping("/{id}")
    public ResponseEntity<Beer> getBeer(@PathVariable Long id){
        Optional<Beer> beer = beerRepository.findById(id);
        return beer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }
}
