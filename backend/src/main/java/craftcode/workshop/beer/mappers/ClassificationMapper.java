package craftcode.workshop.beer.mappers;

import craftcode.workshop.beer.dtos.ClassificationDTO;
import craftcode.workshop.beer.model.Classification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClassificationMapper {
    ClassificationDTO toClassificationDTO(Classification classification );
    Classification toEntity(ClassificationDTO classificationDTO);
}
