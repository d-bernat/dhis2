package org.dhis2.data_element_rest.api.v1.model.elements;

import lombok.Data;
import lombok.Generated;

import java.util.List;

@Data
@Generated
public class DataElementDTO
{
    private String id;
    private String displayName;
    private List<DataElementGroupsItemDTO> dataElementGroups;
}
