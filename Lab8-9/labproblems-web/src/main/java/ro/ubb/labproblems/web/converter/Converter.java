package ro.ubb.labproblems.web.converter;

import ro.ubb.labproblems.core.model.BaseEntity;
import ro.ubb.labproblems.web.dto.BaseDto;

public interface Converter<Model extends BaseEntity<Long>, Dto extends BaseDto> {

    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);

}
