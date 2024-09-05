package craftcode.workshop.beer.controller;

import craftcode.workshop.beer.model.Brewery;
import craftcode.workshop.beer.repository.BreweryRepository;
import craftcode.workshop.beer.services.BreweryService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    BreweryService breweryService;

    @GetMapping("/{id}")
    public ResponseEntity<Brewery> getBreweryById(@PathVariable long id) {
        Optional<Brewery> brewery = breweryService.getBreweryById(id);
        return ResponseEntity.ok(brewery.orElseThrow());
    }
    @GetMapping("/")
    public ResponseEntity<List<Brewery>> getBreweries() {
        List<Brewery> all = breweryService.getAllBreweries();
        return ResponseEntity.ok(all);
    }
}
