package craftcode.workshop.beer.services;

import craftcode.workshop.beer.model.Beer;
import craftcode.workshop.beer.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class BeerService {

    @Autowired
    BeerRepository beerRepository;

    public Optional<Beer> getBeerById(Long id){
        return beerRepository.findById(id);
    }

    public List<Beer> getAllBeers(){
        return beerRepository.findAll();
    }
    public List<Beer> searchBeerByNameOrAlcoholPercentage(String input) {
        return beerRepository.findBeersByNameAndAlcoholPercentage(input);
    }

    public Beer saveBeer(Beer beer){
        return beerRepository.save(beer);
    }

    public Optional<Beer> updateBeer(long l, Beer updatedBeer) {
        return beerRepository.findById(l).map(existingBeer -> {
            existingBeer.setName(updatedBeer.getName());
            existingBeer.setAlcoholPercentage(updatedBeer.getAlcoholPercentage());
            existingBeer.setBeerType(updatedBeer.getBeerType());
            existingBeer.setBrewery(updatedBeer.getBrewery());
            return beerRepository.save(existingBeer);
        });
    }

    public List<Beer> getBeersByBreweryId(long id) {
        return beerRepository.findBeersByBreweryId(id);
    }

    public boolean deleteBeer(Long beerId) {
        if(beerRepository.existsById(beerId)){
            beerRepository.deleteById(beerId);
            return true;
        }
        return false;
    }

    public Page<Beer> getAllBeersFiltered(int pageNr, int pageSize) {
        return beerRepository.findAll(PageRequest.of(pageNr, pageSize));
    }
}
