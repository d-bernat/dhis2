package org.dhis2.data_element_rest.domain.elementgroups;

import lombok.Data;
import lombok.Generated;

import java.util.List;

@Data
@Generated
public class DataElementGroup
{
    private String id;
    private String displayName;
    private List<DataElementsItem> dataElements;
}
