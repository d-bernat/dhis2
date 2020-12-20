package org.dhis2.data_element_rest.api.v1.model.elementgroups;

import lombok.Data;
import lombok.Generated;

import java.util.List;

@Data
@Generated
public class DataElementGroupDTO
{
    private String id;
    private String displayName;
    private List<DataElementsItemDTO> dataElements;
}
