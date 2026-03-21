package org.example.mapper;

import org.example.dto.ContactDto;
import org.example.entity.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ContactMapper {

    ContactDto toContactResponseDto(Contact contact);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "hotel", ignore = true)
    Contact contactDtoToContact(ContactDto contactDto);

}
