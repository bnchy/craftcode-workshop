package craftcode.workshop.beer.controller;

import craftcode.workshop.beer.model.Beer;

import craftcode.workshop.beer.services.BeerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.html.Option;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/beers")
public class BeerController {

    @Autowired
    BeerService beerService;

    @GetMapping("/{id}")
    public ResponseEntity<Beer> getBeer(@PathVariable Long id){
        Optional<Beer> beer = beerService.getBeerById(id);
        return beer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping()
    public ResponseEntity<Page<Beer>> getBeers(@RequestParam(defaultValue = "1") int pageNr, @RequestParam(defaultValue = "12") int pageSize) {
        int pageIndex = pageNr > 0 ? pageNr - 1 : 0;
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<Beer> beers = beerService.getAllBeers(pageable);
        if (beers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(beers);
    }
    @GetMapping("/search")
    public ResponseEntity<Page<Beer>> searchBeers(@RequestParam(defaultValue = "1") String searchTerm, @RequestParam(defaultValue = "12") int pageNr, @RequestParam int pageSize) {
        int pageIndex = pageNr > 0 ? pageNr - 1 : 0;
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<Beer> beers = beerService.searchBeerByNameOrAlcoholPercentage(searchTerm, pageable);
        return ResponseEntity.ok(beers);
    }

    @PostMapping()
    public ResponseEntity<Beer> addBeer(@RequestBody Beer beer, HttpServletRequest request, UriComponentsBuilder ucb){
        Beer savedBeer = beerService.saveBeer(beer);
        URI locationOfNewBeer = ucb.path("/beers/{id}").buildAndExpand(savedBeer.getId()).toUri();
        return ResponseEntity.created(locationOfNewBeer).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Beer> updateBeer(@PathVariable long id, @RequestBody Beer beerDetails) {
        Optional<Beer> updatedBeer = beerService.updateBeer(id, beerDetails);

        return updatedBeer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());

    }

    @GetMapping("by-brewery/{breweryId}")
    public ResponseEntity<List<Beer>> getBeersByBreweryId(@PathVariable Long breweryId) {
        List<Beer> beers = beerService.getBeersByBreweryId(breweryId);
        return ResponseEntity.ok(beers);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBeer(@PathVariable Long id) {
        boolean deleted = beerService.deleteBeer(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
