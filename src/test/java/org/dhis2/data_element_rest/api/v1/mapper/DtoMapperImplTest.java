package org.dhis2.data_element_rest.api.v1.mapper;

import org.dhis2.data_element_rest.api.v1.model.Items;
import org.dhis2.data_element_rest.domain.elementgroups.DataElementGroup;
import org.dhis2.data_element_rest.domain.elementgroups.DataElementGroups;
import org.dhis2.data_element_rest.domain.elementgroups.DataElementsItem;
import org.dhis2.data_element_rest.domain.elements.DataElement;
import org.dhis2.data_element_rest.domain.elements.DataElementGroupsItem;
import org.dhis2.data_element_rest.domain.elements.DataElements;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DtoMapperImplTest
{
    private final static String GROUPS_ID = "GROUP";
    private final static String ELEMENT_ID = "ELEMENT";
    private final static String DISPLAY_NAME = "TEST";

    private final DtoMapper mapper = new DtoMapperImpl();

    @Test
    public void transform_DataElements_To_Items_When_Called()
    {
        //Arrange
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

        //Act
        Items items = mapper.toItems(dataElements);

        //Assert
        assertEquals(items.getItems().size(), 1);
    }

    @Test
    public void transform_DataElementGroups_To_Items_When_Called()
    {
        //Arrange
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

        //Act
        Items items = mapper.toItems(dataElementGroups);

        //Assert
        assertEquals(items.getItems().size(), 1);
    }
}