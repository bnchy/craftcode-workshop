package craftcode.workshop.beer.services;

import craftcode.workshop.beer.model.Brewery;
import craftcode.workshop.beer.repository.BreweryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BreweryService {

    @Autowired
    BreweryRepository breweryRepository;

    public Optional<Brewery> getBreweryById(Long id) {
        return breweryRepository.findById(id);
    }

    public List<Brewery> getAllBreweries() {
        return breweryRepository.findAll();
    }
}

