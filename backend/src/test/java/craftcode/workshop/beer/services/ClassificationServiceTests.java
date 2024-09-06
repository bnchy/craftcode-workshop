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
        classification1.setUsedGrainType(GrainTypes.BARELY);

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
        classification1.setUsedGrainType(GrainTypes.BARELY);
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


}
