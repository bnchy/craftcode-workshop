package craftcode.workshop.beer.services;

import craftcode.workshop.beer.model.Beer;
import craftcode.workshop.beer.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Beer saveBeer(Beer beer){
        return beerRepository.save(beer);
    }
}
