package craftcode.workshop.beer.controller;

import craftcode.workshop.beer.model.Brewery;
import craftcode.workshop.beer.repository.BreweryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/breweries")
public class BreweryController {

    private final BreweryRepository breweryRepository;

    public BreweryController(BreweryRepository breweryRepository) {
        this.breweryRepository = breweryRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brewery> getBreweryById(@PathVariable long id) {
        Optional<Brewery> brewery = breweryRepository.findById(id);
        return ResponseEntity.ok(brewery.orElseThrow());
    }
    @GetMapping("/")
    public ResponseEntity<List<Brewery>> getBreweries() {
        List<Brewery> all = breweryRepository.findAll();
        return ResponseEntity.ok(all);
    }
}
