package com.coyjiv.springbankadminpanel.transfer;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;


public class DTOMapperFacade<E, D> {
    private final Class<E> entityClass;
    private final Class<D> dtoClass;

    private final ModelMapper modelMapper = new ModelMapper();

    public DTOMapperFacade(final Class<E> eClass, final Class<D> dClass) {
        entityClass = eClass;
        dtoClass = dClass;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public E convertToEntity(final D dto) {
        try {
            final E entity = modelMapper.map(dto, entityClass);
            decorateEntity(entity, dto);
            System.out.println("Converted DTO to Entity: " + entity);
            return entity;
        } catch (Exception e) {
            System.out.println("Error converting DTO to Entity: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public D convertToDto(final E entity) {
        try {
            final D dto = modelMapper.map(entity, dtoClass);
            decorateDto(dto, entity);
            System.out.println("Converted Entity to DTO: " + dto);
            return dto;
        } catch (Exception e) {
            System.out.println("Error converting Entity to DTO: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    protected void decorateEntity(final E entity, final D dto) {

    }

    protected void decorateDto(final D dto, final E entity) {

    }
}