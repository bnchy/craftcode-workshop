package craftcode.workshop.beer.repository;

import craftcode.workshop.beer.model.Classification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassificationRepository extends JpaRepository<Classification, Long> {

}
