package org.dhis2.data_element_rest.api.v1.mapper;

import org.dhis2.data_element_rest.api.v1.model.elementgroups.DataElementGroupsDTO;
import org.dhis2.data_element_rest.api.v1.model.elements.DataElementsDTO;
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

public class TrivialMapperImplTest
{
    private final static String GROUPS_ID = "GROUP";
    private final static String ELEMENT_ID = "ELEMENT";
    private final static String DISPLAY_NAME = "TEST";

    private final TrivialMapper mapper = new TrivialMapperImpl();

    @Test
    public void transform_DataElements_To_DataElementsDTO_When_Called()
    {
        //Arrange
        DataElements dataElements = new DataElements();
        DataElementGroupsItem dataElementGroupsItem = new DataElementGroupsItem();
        dataElementGroupsItem.setId(GROUPS_ID);
        DataElement dataElement = new DataElement();
        dataElement.setId(ELEMENT_ID);
        dataElement.setDisplayName(DISPLAY_NAME);
        List<DataElementGroupsItem> items = new ArrayList<>();
        items.add(dataElementGroupsItem);
        dataElement.setDataElementGroups(items);
        List<DataElement> elements = new ArrayList<>();
        elements.add(dataElement);
        dataElements.setDataElements(elements);
        //Act
        DataElementsDTO dataElementsDTO = mapper.toDataElementsDTO(dataElements);

        //Assert
        assertEquals(dataElementsDTO.getDataElements().size(), 1);
    }

    @Test
    public void transform_DataElementGroups_To_DataElementGroupsDTO_When_Called()
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
        DataElementGroupsDTO dataElementsDTO = mapper.toDataElementGroupsDTO(dataElementGroups);

        //Assert
        assertEquals(dataElementsDTO.getDataElementGroups().size(), 1);
    }


}