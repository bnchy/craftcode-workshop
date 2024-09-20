package craftcode.workshop.beer.controller;

import craftcode.workshop.beer.model.Beer;
import craftcode.workshop.beer.model.Brewery;
import craftcode.workshop.beer.services.BreweryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.html.Option;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


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
    @GetMapping
    public ResponseEntity<List<Brewery>> getBreweries() {
        List<Brewery> all = breweryService.getAllBreweries();
        return ResponseEntity.ok(all);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Brewery> updateBrewery(@PathVariable long id, @RequestBody Brewery updatedBrewery) {
        Optional<Brewery> brewery = breweryService.updateBrewery(id, updatedBrewery);

        if (brewery.isPresent()) {
            return ResponseEntity.ok(brewery.get());
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{breweryId}/beers/{beerId}")
    public ResponseEntity<Void> unlinkBeer(@PathVariable Long breweryId, @PathVariable Long beerId) {
        boolean unlinked = breweryService.unlinkBeerFromBrewery(breweryId, beerId);

        if (unlinked) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBreweryById(@PathVariable Long id) {
        boolean deleted = breweryService.deleteBrewery(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<Brewery> saveBrewery(@RequestBody Brewery brewery, HttpServletRequest request, UriComponentsBuilder ucb) {

        Brewery savedBrewery = breweryService.saveBrewery(brewery);
        URI locationOfNewBrewery = ucb.path("/beers/{id}").buildAndExpand(savedBrewery.getId()).toUri();
        return ResponseEntity.created(locationOfNewBrewery).build();
    }
}
