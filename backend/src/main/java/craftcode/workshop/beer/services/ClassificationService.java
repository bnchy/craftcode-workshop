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

    public Optional<Classification> updateClassification(Long id, Classification updatedClassification) {
        return classificationRepository.findById(id).map(existingClassification -> {
            existingClassification.setCountry(updatedClassification.getCountry());
            existingClassification.setFermentationType(updatedClassification.getFermentationType());
            existingClassification.setNamesAndOrigins(updatedClassification.getNamesAndOrigins());
            existingClassification.setUsedGrainType(updatedClassification.getUsedGrainType());

            return classificationRepository.save(existingClassification);
        });
    }

    public boolean deleteClassification(Long id) {
        if (classificationRepository.existsById(id)) {
            classificationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Classification saveClassification(Classification classification) {
        return classificationRepository.save(classification);
    }
}
