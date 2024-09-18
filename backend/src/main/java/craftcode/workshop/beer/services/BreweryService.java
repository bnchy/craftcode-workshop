package craftcode.workshop.beer.services;

import craftcode.workshop.beer.model.Beer;
import craftcode.workshop.beer.model.Brewery;
import craftcode.workshop.beer.repository.BeerRepository;
import craftcode.workshop.beer.repository.BreweryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BreweryService {

    @Autowired
    BreweryRepository breweryRepository;

    @Autowired
    BeerRepository beerRepository;

    public Optional<Brewery> getBreweryById(Long id) {
        return breweryRepository.findById(id);
    }

    public List<Brewery> getAllBreweries() {
        return breweryRepository.findAll();
    }

    public Optional<Brewery> updateBrewery(Long id, Brewery update) {
        return breweryRepository.findById(id).map(existingBrewery -> {
            existingBrewery.setName(update.getName());
            existingBrewery.setLocation(update.getLocation());
            return breweryRepository.save(existingBrewery);
        });
    }

    public Optional<Brewery> unlinkBeer(long breweryId, long beerId) {
        return breweryRepository.findById(breweryId).map(existingBrewery -> {
            Beer beerToRemove = beerRepository.findById(beerId)
                    .orElseThrow(() -> new RuntimeException("Beer with id " + beerId + " not found"));
            existingBrewery.getBeers().remove(beerToRemove);
            beerToRemove.setBrewery(null);
            beerRepository.save(beerToRemove);

            return breweryRepository.save(existingBrewery);
        });
    }
}

