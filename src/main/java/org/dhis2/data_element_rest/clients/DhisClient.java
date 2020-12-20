package org.dhis2.data_element_rest.clients;

import org.dhis2.data_element_rest.domain.elementgroups.DataElementGroups;
import org.dhis2.data_element_rest.domain.elements.DataElements;

import java.net.URISyntaxException;

public interface DhisClient
{
    DataElements getDataElements() throws URISyntaxException;
    DataElementGroups getDataElementGroups() throws URISyntaxException;
}
