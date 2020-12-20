package org.dhis2.data_element_rest.api.v1.mapper;

import org.dhis2.data_element_rest.api.v1.model.Items;
import org.dhis2.data_element_rest.domain.elementgroups.DataElementGroups;
import org.dhis2.data_element_rest.domain.elements.DataElements;

public interface DtoMapper
{
    Items toItems(DataElements dataElements);

    Items toItems(DataElementGroups dataElements);
}
