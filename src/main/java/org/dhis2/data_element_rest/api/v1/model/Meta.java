package org.dhis2.data_element_rest.api.v1.model;

import lombok.Data;
import lombok.Generated;

@Data
@Generated
public class Meta
{
    private ItemType itemType;
    private ItemType nestedItemType;
    private Integer count;
}
