package craftcode.workshop.beer.services;

import craftcode.workshop.beer.enums.Country;
import craftcode.workshop.beer.enums.FermentationType;
import craftcode.workshop.beer.enums.GrainTypes;
import craftcode.workshop.beer.enums.NamesAndOrigins;
import craftcode.workshop.beer.model.Classification;
import craftcode.workshop.beer.repository.ClassificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ClassificationServiceTests {

    @InjectMocks
    ClassificationService classificationService;

    @Mock
    ClassificationRepository classificationRepository;

    List<Classification> classifications;

    @BeforeEach
    void setUp(){
        Classification classification1 = new Classification();
        classification1.setId(1L);
        classification1.setFermentationType(FermentationType.SPONTANEOUS);
        classification1.setCountry(Country.BELGIUM);
        classification1.setNamesAndOrigins(NamesAndOrigins.ABBEY_BEER);
        classification1.setUsedGrainType(GrainTypes.BARLEY);

        Classification classification2 = new Classification();
        classification2.setId(2L);
        classification2.setFermentationType(FermentationType.SPONTANEOUS);
        classification2.setCountry(Country.GERMANY);
        classification2.setNamesAndOrigins(NamesAndOrigins.DARK_BEER);
        classification2.setUsedGrainType(GrainTypes.OATS);

        classifications = List.of(classification1, classification2);
    }

    @Test
    void shouldReturnAClassification() {
        Classification classification1 = new Classification();
        classification1.setId(1L);
        classification1.setNamesAndOrigins(NamesAndOrigins.ABBEY_BEER);
        classification1.setCountry(Country.BELGIUM);
        classification1.setUsedGrainType(GrainTypes.BARLEY);
        classification1.setFermentationType(FermentationType.SPONTANEOUS);

        when(classificationRepository.findById(1L)).thenReturn(Optional.of(classification1));

        Optional<Classification> foundClassification = classificationService.getClassificationById(1L);
        assertThat(foundClassification).isPresent();

        Country country = foundClassification.get().getCountry();
        assertThat(country).isEqualTo(Country.BELGIUM);
    }

    @Test
    void shouldReturnAllClassifications() {
        when(classificationRepository.findAll()).thenReturn(classifications);

        List<Classification> foundClassifications = classificationService.getAllClassifications();
        assertThat(foundClassifications)
                .isNotEmpty()
                .hasSize(2);

        NamesAndOrigins foundNamesAndOrigins = foundClassifications.get(0).getNamesAndOrigins();

        assertThat(foundNamesAndOrigins).isEqualTo(NamesAndOrigins.ABBEY_BEER);
    }

    @Test
    void shouldSaveAClassification(){
        Classification classification = new Classification();
        classification.setId(1L);
        classification.setNamesAndOrigins(NamesAndOrigins.ABBEY_BEER);
        classification.setCountry(Country.BELGIUM);

        when(classificationRepository.save(classification)).thenReturn(classification);

        Classification savedClassification = classificationService.saveClassification(classification);

        assertThat(savedClassification.getNamesAndOrigins()).isEqualTo(classification.getNamesAndOrigins());
    }

    @Test
    void shouldUpdateAClassification() {
        Classification exisitingClassification = new Classification();
        exisitingClassification.setId(1L);
        exisitingClassification.setNamesAndOrigins(NamesAndOrigins.ABBEY_BEER);
        exisitingClassification.setCountry(Country.BELGIUM);
        exisitingClassification.setUsedGrainType(GrainTypes.BARLEY);
        exisitingClassification.setFermentationType(FermentationType.SPONTANEOUS);

        Classification updatedClassification = new Classification();
        updatedClassification.setId(1L);
        updatedClassification.setNamesAndOrigins(NamesAndOrigins.DARK_BEER);
        updatedClassification.setCountry(Country.GERMANY);
        updatedClassification.setUsedGrainType(GrainTypes.OATS);
        updatedClassification.setFermentationType(FermentationType.COOL);

        when(classificationRepository.findById(1L)).thenReturn(Optional.of(exisitingClassification));
        when(classificationRepository.save(exisitingClassification)).thenReturn(exisitingClassification);

        Optional<Classification> foundClassification = classificationService.updateClassification(1L, updatedClassification);

        assertThat(foundClassification).isPresent();
        assertThat(foundClassification.get().getCountry()).isEqualTo(Country.GERMANY);
    }

    @Test
    void shouldDeleteAClassification() {
        Long id = 1L;

        when(classificationRepository.existsById(id)).thenReturn(true);

        boolean result = classificationService.deleteClassification(1L);

        verify(classificationRepository).deleteById(id);

        assertThat(result).isTrue();
    }
}
