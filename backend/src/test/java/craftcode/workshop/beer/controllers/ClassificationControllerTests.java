package craftcode.workshop.beer.controllers;

import craftcode.workshop.beer.config.TestSecurityConfig;
import craftcode.workshop.beer.controller.ClassificationController;
import craftcode.workshop.beer.enums.Country;
import craftcode.workshop.beer.enums.FermentationType;
import craftcode.workshop.beer.enums.GrainTypes;
import craftcode.workshop.beer.enums.NamesAndOrigins;
import craftcode.workshop.beer.model.Classification;
import craftcode.workshop.beer.services.ClassificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClassificationControllerTests {

    @Mock
    ClassificationService classificationService;

    @InjectMocks
    ClassificationController classificationController;

    List<Classification> classifications;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Classification classification1 = new Classification();
        classification1.setId(1L);
        classification1.setUsedGrainType(GrainTypes.BARELY);
        classification1.setNamesAndOrigins(NamesAndOrigins.ABBEY_BEER);
        classification1.setCountry(Country.BELGIUM);
        classification1.setFermentationType(FermentationType.SPONTANEOUS);
        Classification classification2 = new Classification();
        classification2.setId(2L);
        classification2.setUsedGrainType(GrainTypes.OATS);
        classification2.setNamesAndOrigins(NamesAndOrigins.DARK_BEER);
        classification2.setCountry(Country.GERMANY);
        classification2.setFermentationType(FermentationType.COOL);

        classifications = List.of(classification1, classification2);
    }

    @Test
    void shouldReturnAllClassifications() {
        when(classificationService.getAllClassifications()).thenReturn(classifications);

        ResponseEntity<List<Classification>> response = classificationController.getClassification();

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isEqualTo(2);
    }

    @Test
    void shouldReturnClassificationById() {
        when(classificationService.getClassificationById(1L)).thenReturn(Optional.of(classifications.get(0)));

        ResponseEntity<Classification> response = classificationController.getClassificationById(1L);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(1L);
    }
    @Test
    void shouldUpdateAClassification() {
        Classification exisitingClassification = new Classification();
        exisitingClassification.setId(1L);
        exisitingClassification.setUsedGrainType(GrainTypes.BARELY);
        exisitingClassification.setNamesAndOrigins(NamesAndOrigins.ABBEY_BEER);
        exisitingClassification.setCountry(Country.BELGIUM);
        exisitingClassification.setFermentationType(FermentationType.COOL);

        Classification updatedClassification = new Classification();
        updatedClassification.setId(1L);
        updatedClassification.setUsedGrainType(GrainTypes.OATS);
        updatedClassification.setNamesAndOrigins(NamesAndOrigins.DARK_BEER);
        updatedClassification.setCountry(Country.GERMANY);
        updatedClassification.setFermentationType(FermentationType.SPONTANEOUS);

        when(classificationService.updateClassification(1L, updatedClassification)).thenReturn(Optional.of(updatedClassification));

        ResponseEntity<Classification> response = classificationController.updateClassification(1L, updatedClassification);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getCountry()).isEqualTo(updatedClassification.getCountry());
    }
}
