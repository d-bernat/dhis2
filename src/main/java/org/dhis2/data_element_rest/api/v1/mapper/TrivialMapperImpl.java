package org.dhis2.data_element_rest.api.v1.mapper;

import org.dhis2.data_element_rest.api.v1.model.elementgroups.DataElementGroupDTO;
import org.dhis2.data_element_rest.api.v1.model.elementgroups.DataElementGroupsDTO;
import org.dhis2.data_element_rest.api.v1.model.elementgroups.DataElementsItemDTO;
import org.dhis2.data_element_rest.api.v1.model.elements.DataElementDTO;
import org.dhis2.data_element_rest.api.v1.model.elements.DataElementGroupsItemDTO;
import org.dhis2.data_element_rest.api.v1.model.elements.DataElementsDTO;
import org.dhis2.data_element_rest.domain.elementgroups.DataElementGroups;
import org.dhis2.data_element_rest.domain.elements.DataElements;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class TrivialMapperImpl implements TrivialMapper
{
    @Override
    public DataElementsDTO toDataElementsDTO(DataElements dataElements)
    {
        if(dataElements != null && dataElements.getDataElements() != null && dataElements.getDataElements().size() > 0)
        {
            DataElementsDTO dataElementsDTO = new DataElementsDTO();
            dataElementsDTO.setDataElements(dataElements.getDataElements().parallelStream().map(elem ->
            {
                DataElementDTO dataElementDTO = new DataElementDTO();
                dataElementDTO.setId(elem.getId());
                dataElementDTO.setDisplayName(elem.getDisplayName());
                dataElementDTO.setDataElementGroups(
                        elem.getDataElementGroups().parallelStream().map(group ->
                        {
                            DataElementGroupsItemDTO item = new DataElementGroupsItemDTO();
                            item.setId(group.getId());
                            return item;
                        }).collect(Collectors.toList()));
                return dataElementDTO;
            }).collect(Collectors.toList()));
            return dataElementsDTO;
        }
        return null;
    }

    @Override
    public DataElementGroupsDTO toDataElementGroupsDTO(DataElementGroups dataElementGroups)
    {
        if(dataElementGroups != null && dataElementGroups.getDataElementGroups() != null && dataElementGroups.getDataElementGroups().size() > 0)
        {
            DataElementGroupsDTO dataElementGroupsDTO = new DataElementGroupsDTO();
            dataElementGroupsDTO.setDataElementGroups(dataElementGroups.getDataElementGroups().parallelStream().map(elem ->
            {
                DataElementGroupDTO dataElementGroupDTO = new DataElementGroupDTO();
                dataElementGroupDTO.setId(elem.getId());
                dataElementGroupDTO.setDisplayName(elem.getDisplayName());
                dataElementGroupDTO.setDataElements(
                        elem.getDataElements().parallelStream().map(group ->
                        {
                            DataElementsItemDTO item = new DataElementsItemDTO();
                            item.setId(group.getId());
                            return item;
                        }).collect(Collectors.toList()));
                return dataElementGroupDTO;
            }).collect(Collectors.toList()));
            return dataElementGroupsDTO;
        }
        return null;
    }
}
