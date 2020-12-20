package org.dhis2.data_element_rest.services;

import org.dhis2.data_element_rest.api.v1.model.Items;
import org.dhis2.data_element_rest.domain.elementgroups.DataElementGroups;
import org.dhis2.data_element_rest.domain.elements.DataElements;

import java.net.URISyntaxException;

public interface DataElementsService
{
    Items getDataElementItems() throws URISyntaxException;
    Items getDataElementGroupItems() throws URISyntaxException;
}
