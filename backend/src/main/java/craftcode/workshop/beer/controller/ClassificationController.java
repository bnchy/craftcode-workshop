package craftcode.workshop.beer.controller;

import craftcode.workshop.beer.model.Brewery;
import craftcode.workshop.beer.model.Classification;
import craftcode.workshop.beer.services.ClassificationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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

    @PutMapping("/{id}")
    public ResponseEntity<Classification> updateClassification(@PathVariable Long id, @RequestBody Classification updatedClassification) {
        Optional<Classification> classification = classificationService.updateClassification(id, updatedClassification);

        if (classification.isPresent()) {
            return ResponseEntity.ok(classification.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassification(@PathVariable Long id) {
        boolean deleted = classificationService.deleteClassification(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Classification> saveClassification(@RequestBody Classification classification, HttpServletRequest request, UriComponentsBuilder ucb) {
        Classification savedClassification = classificationService.saveClassification(classification);
        URI locationOfNewClassification = ucb.path("/classifications/{id}").buildAndExpand(savedClassification.getId()).toUri();
        return ResponseEntity.created(locationOfNewClassification).body(savedClassification);
    }
}
