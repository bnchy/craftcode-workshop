package craftcode.workshop.beer.repository;

import craftcode.workshop.beer.model.Beer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BeerRepository extends JpaRepository<Beer, Long> {}
