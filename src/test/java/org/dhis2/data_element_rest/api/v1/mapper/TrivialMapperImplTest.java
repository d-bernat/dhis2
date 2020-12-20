package org.dhis2.data_element_rest.api.v1.mapper;

import org.dhis2.data_element_rest.api.v1.model.DataElementsDTO;
import org.dhis2.data_element_rest.domain.DataElement;
import org.dhis2.data_element_rest.domain.DataElementGroupsItem;
import org.dhis2.data_element_rest.domain.DataElements;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrivialMapperImplTest
{
    private final static String GROUPS_ID = "GROUP";
    private final static String ELEMENT_ID = "ELEMENT";
    private final static String DISPLAY_NAME = "TEST";

    private TrivialMapper mapper = new TrivialMapperImpl();

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

}