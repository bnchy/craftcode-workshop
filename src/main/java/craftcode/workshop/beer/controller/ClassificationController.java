package craftcode.workshop.beer.controller;

import craftcode.workshop.beer.model.Classification;
import craftcode.workshop.beer.services.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/classifications")
public class ClassificationController {

    @Autowired
    ClassificationService classificationService;

    @GetMapping("/{id}")
    public ResponseEntity<Classification> getClassificationById(@PathVariable long id){
        Optional<Classification> classification = classificationService.getClassificationById(id);
        return classification.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Classification>> getClassification() {
        List<Classification> classifications = classificationService.getAllClassifications();
        return ResponseEntity.ok(classifications);
    }
}
