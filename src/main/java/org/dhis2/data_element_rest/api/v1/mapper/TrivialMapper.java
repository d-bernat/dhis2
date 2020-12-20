package org.dhis2.data_element_rest.api.v1.mapper;

import org.dhis2.data_element_rest.api.v1.model.DataElementsDTO;
import org.dhis2.data_element_rest.domain.DataElements;

public interface TrivialMapper
{
    DataElementsDTO toDataElementsDTO(DataElements dataElements);
}
