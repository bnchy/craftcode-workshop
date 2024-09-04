package craftcode.workshop.beer;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BeerRepository extends CrudRepository<Beer, Long> {

    @Override
    List<Beer> findAll();
}
