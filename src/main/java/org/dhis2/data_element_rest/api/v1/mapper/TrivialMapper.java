package org.dhis2.data_element_rest.api.v1.mapper;

import org.dhis2.data_element_rest.api.v1.model.elementgroups.DataElementGroupsDTO;
import org.dhis2.data_element_rest.api.v1.model.elements.DataElementsDTO;
import org.dhis2.data_element_rest.domain.elementgroups.DataElementGroups;
import org.dhis2.data_element_rest.domain.elements.DataElements;

public interface TrivialMapper
{
    DataElementsDTO toDataElementsDTO(DataElements dataElements);
    DataElementGroupsDTO toDataElementGroupsDTO(DataElementGroups dataElementGroups);
}
