package craftcode.workshop.beer.services;

import craftcode.workshop.beer.model.Classification;
import craftcode.workshop.beer.repository.ClassificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassificationService {
    @Autowired
    ClassificationRepository classificationRepository;


    public Optional<Classification> getClassificationById(Long id) {
        return classificationRepository.findById(id);
    }

    public List<Classification> getAllClassifications() {
        return classificationRepository.findAll();
    }
}
