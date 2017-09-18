package utill;

public interface Converter<ENTITY, DTO> {
    DTO convertEntityToDto(ENTITY entity);

    ENTITY convertDtoToEntity(DTO dto);
}
