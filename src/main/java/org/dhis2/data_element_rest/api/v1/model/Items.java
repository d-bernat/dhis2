package org.dhis2.data_element_rest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Generated;

import java.util.List;

@Data
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Items
{
    private Meta meta;
    private Error error;
    private List<Item> items;
}
