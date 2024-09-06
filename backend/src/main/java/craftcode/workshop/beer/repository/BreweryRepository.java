package craftcode.workshop.beer.repository;

import craftcode.workshop.beer.model.Brewery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreweryRepository extends JpaRepository<Brewery, Long> {}
