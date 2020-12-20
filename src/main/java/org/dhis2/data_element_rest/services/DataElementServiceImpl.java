package org.dhis2.data_element_rest.services;

import org.dhis2.data_element_rest.api.v1.mapper.DtoMapper;
import org.dhis2.data_element_rest.api.v1.model.Items;
import org.dhis2.data_element_rest.clients.DhisClient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;

@Service
public class DataElementServiceImpl implements DataElementsService
{
    private final DtoMapper dtoMapper;
    private final DhisClient dhisClient;

    public DataElementServiceImpl(DtoMapper dtoMapper, DhisClient dhisClient)
    {
        this.dtoMapper = dtoMapper;
        this.dhisClient = dhisClient;
    }

    @Override
    public Items getDataElementItems() throws URISyntaxException
    {
        return dtoMapper.toItems(dhisClient.getDataElements());
    }

    @Override
    public Items getDataElementGroupItems() throws URISyntaxException
    {
        return dtoMapper.toItems(dhisClient.getDataElementGroups());
    }
}
