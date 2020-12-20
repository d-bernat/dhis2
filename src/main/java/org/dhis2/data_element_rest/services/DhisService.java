package org.dhis2.data_element_rest.services;

import org.dhis2.data_element_rest.api.v1.model.DataElementsDTO;

import java.net.URISyntaxException;

public interface DhisService
{
    DataElementsDTO getDataElements() throws URISyntaxException;
}
