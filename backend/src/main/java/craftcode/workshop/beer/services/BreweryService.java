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

    public boolean unlinkBeerFromBrewery(long breweryId, long beerId) {
        Optional<Brewery> breweryOptional = breweryRepository.findById(breweryId);

        if (breweryOptional.isPresent()) {
            Brewery brewery = breweryOptional.get();
            Optional<Beer> beerOptional = beerRepository.findById(beerId);

            if (beerOptional.isPresent()) {
                Beer beerToRemove = beerOptional.get();
                brewery.getBeers().remove(beerToRemove);
                beerToRemove.setBrewery(null);

                beerRepository.save(beerToRemove);
                breweryRepository.save(brewery);

                return true;
            }
        }
        return false;
    }

    public boolean deleteBrewery(long breweryId) {
        if(breweryRepository.existsById(breweryId)) {
            breweryRepository.deleteById(breweryId);
            return true;
        }
        return false;
    }
}

