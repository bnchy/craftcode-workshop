package craftcode.workshop.beer.controller;

import craftcode.workshop.beer.model.Beer;

import craftcode.workshop.beer.services.BeerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/beers")
public class BeerController {

    @Autowired
    BeerService beerService;


    @GetMapping
    public ResponseEntity<List<Beer>> getBeers() {
        List<Beer> beers = beerService.getAllBeers();
        if (beers.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(beers);
        }}

    @GetMapping("/{id}")
    public ResponseEntity<Beer> getBeer(@PathVariable Long id){
        Optional<Beer> beer = beerService.getBeerById(id);
        return beer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Beer> addBeer(@RequestBody Beer beer, HttpServletRequest request, UriComponentsBuilder ucb){
        Beer savedBeer = beerService.saveBeer(beer);
        URI locationOfNewBeer = ucb.path("/beers/{id}").buildAndExpand(savedBeer.getId()).toUri();
        return ResponseEntity.created(locationOfNewBeer).build();
    }


}
