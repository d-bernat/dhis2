package org.dhis2.data_element_rest.domain;

import lombok.Data;
import lombok.Generated;

import java.util.List;

@Data
@Generated
public class DataElement
{
    private String id;
    private String displayName;
    private List<DataElementGroupsItem> dataElementGroups;
}
