package org.example.mapper;

import org.example.dto.response.ContactResponseDto;
import org.example.entity.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ContactMapper {

    ContactResponseDto toContactResponseDto(Contact contact);

}
