package craftcode.workshop.beer.repository;

import craftcode.workshop.beer.model.Beer;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;


public interface BeerRepository extends JpaRepository<Beer, Long> {


    @NonNull
    Page<Beer> findAll(Pageable pageable);

    @NonNull
    List<Beer> findAll();

    @Query("SELECT b FROM Beer b JOIN b.brewery br WHERE  br.id = :id ")
    List<Beer> findBeersByBreweryId(Long id);

    @Query("SELECT b FROM Beer b WHERE LOWER (b.name) LIKE LOWER(CONCAT('%', :input, '%')) OR CAST(b.alcoholPercentage AS string) LIKE :input")
    Page<Beer> findBeersByNameAndAlcoholPercentage(String input, Pageable pageable);
}
