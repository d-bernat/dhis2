package org.dhis2.data_element_rest.services;

import org.dhis2.data_element_rest.api.v1.mapper.DtoMapperImpl;
import org.dhis2.data_element_rest.api.v1.model.Items;
import org.dhis2.data_element_rest.clients.DhisClient;
import org.dhis2.data_element_rest.domain.elementgroups.DataElementGroup;
import org.dhis2.data_element_rest.domain.elementgroups.DataElementGroups;
import org.dhis2.data_element_rest.domain.elementgroups.DataElementsItem;
import org.dhis2.data_element_rest.domain.elements.DataElement;
import org.dhis2.data_element_rest.domain.elements.DataElementGroupsItem;
import org.dhis2.data_element_rest.domain.elements.DataElements;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DataElementServiceImplTest
{
    private final static String GROUPS_ID = "GROUP";
    private final static String ELEMENT_ID = "ELEMENT";
    private final static String DISPLAY_NAME = "TEST";

    @Mock
    private DhisClient dhisClient;

    private DataElementsService dataElementsService;
    private DataElements dataElements;
    private DataElementGroups dataElementGroups;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        dataElementsService = new DataElementServiceImpl(new DtoMapperImpl(), dhisClient);
        dataElements = getDataElements();
        dataElementGroups = getDataElementGroups();
    }

    @Test
    public void get_DataElement_Items_When_Called() throws URISyntaxException
    {
        //arrange
        when(dhisClient.getDataElements()).thenReturn(dataElements);

        //act
        Items items = dataElementsService.getDataElementItems();

        //assert
        assertEquals(1, items.getItems().size());
        assertEquals(DISPLAY_NAME, items.getItems().get(0).getDisplayName());
    }

    @Test
    public void get_DataElement_Group_Items_When_Called() throws URISyntaxException
    {
        //arrange
        when(dhisClient.getDataElementGroups()).thenReturn(dataElementGroups);

        //act
        Items items = dataElementsService.getDataElementGroupItems();

        //assert
        assertEquals(1, items.getItems().size());
        assertEquals(DISPLAY_NAME, items.getItems().get(0).getDisplayName());
    }

    private DataElementGroups getDataElementGroups()
    {
        DataElementGroups dataElementGroups = new DataElementGroups();
        DataElementGroup dataElementGroup = new DataElementGroup();
        DataElementsItem dataElementsItem = new DataElementsItem();
        dataElementsItem.setId(ELEMENT_ID);
        dataElementGroup.setId(GROUPS_ID);
        dataElementGroup.setDisplayName(DISPLAY_NAME);
        List<DataElementsItem> dataElementsItems = new ArrayList<>();
        dataElementsItems.add(dataElementsItem);
        dataElementGroup.setDataElements(dataElementsItems);
        List<DataElementGroup> dataElementGroupItems = new ArrayList<>();
        dataElementGroupItems.add(dataElementGroup);
        dataElementGroups.setDataElementGroups(dataElementGroupItems);

        return dataElementGroups;
    }

    private DataElements getDataElements()
    {
        DataElements dataElements = new DataElements();
        DataElementGroupsItem dataElementGroupsItem = new DataElementGroupsItem();
        dataElementGroupsItem.setId(GROUPS_ID);
        DataElement dataElement = new DataElement();
        dataElement.setId(ELEMENT_ID);
        dataElement.setDisplayName(DISPLAY_NAME);
        List<DataElementGroupsItem> groupItems = new ArrayList<>();
        groupItems.add(dataElementGroupsItem);
        dataElement.setDataElementGroups(groupItems);
        List<DataElement> elements = new ArrayList<>();
        elements.add(dataElement);
        dataElements.setDataElements(elements);

        return dataElements;
    }
}