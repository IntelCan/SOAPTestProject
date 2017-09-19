package soap.utill;

import org.springframework.stereotype.Service;

@Service
public interface Converter<ENTITY, DTO> {

    DTO convertEntityToDto(ENTITY entity);

    ENTITY convertDtoToEntity(DTO dto);
}
