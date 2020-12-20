package org.dhis2.data_element_rest.api.v1.mapper;

import org.dhis2.data_element_rest.api.v1.model.DataElementDTO;
import org.dhis2.data_element_rest.api.v1.model.DataElementGroupsItemDTO;
import org.dhis2.data_element_rest.api.v1.model.DataElementsDTO;
import org.dhis2.data_element_rest.domain.DataElements;
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
            dataElementsDTO.setDataElements(dataElements.getDataElements().stream().map(elem ->
            {
               DataElementDTO dataElementDTO = new DataElementDTO();
               dataElementDTO.setId(elem.getId());
               dataElementDTO.setDisplayName(elem.getDisplayName());
               dataElementDTO.setDataElementGroups(
               elem.getDataElementGroups().stream().map(group -> {
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
}
