package craftcode.workshop.beer.services;

import craftcode.workshop.beer.model.Beer;
import craftcode.workshop.beer.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Beer> getAllBeers(Pageable pageable){
        return beerRepository.findAll(pageable);
    }
    public Page<Beer> searchBeerByNameOrAlcoholPercentage(String input, Pageable pageable) {
        return beerRepository.findBeersByNameAndAlcoholPercentage(input, pageable);
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


    public Optional<Beer> likeBeer(long id) {
        return beerRepository.findById(id).map(beer -> {
            beer.incrementLikes();
            beer.decrementDislikes();
            beerRepository.save(beer);
            return beer;
        });

    }

    public Optional<Beer> dislikeBeer(long id) {
        return beerRepository.findById(id).map(beer -> {
            beer.incrementDislikes();
            beer.decrementLikes();
            beerRepository.save(beer);

            return beer;
        });
    }
}
