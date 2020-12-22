package org.dhis2.data_element_rest.api.v1.mapper;

import org.dhis2.data_element_rest.api.v1.model.Item;
import org.dhis2.data_element_rest.api.v1.model.ItemType;
import org.dhis2.data_element_rest.api.v1.model.Items;
import org.dhis2.data_element_rest.api.v1.model.Meta;
import org.dhis2.data_element_rest.domain.elementgroups.DataElementGroups;
import org.dhis2.data_element_rest.domain.elementgroups.DataElementsItem;
import org.dhis2.data_element_rest.domain.elements.DataElementGroupsItem;
import org.dhis2.data_element_rest.domain.elements.DataElements;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class DtoMapperImpl implements DtoMapper
{
    @Override
    public Items toItems(DataElements dataElements)
    {
        if (dataElements != null && dataElements.getDataElements() != null
                && dataElements.getDataElements().size() > 0)
        {
            Items items = new Items();
            items.setMeta(getMeta(ItemType.DATA_ELEMENTS, dataElements.getDataElements().size()));
            items.setItems(dataElements.getDataElements()
                                       .parallelStream()
                                       .map(elem ->
                                       {
                                           Item item = new Item();
                                           item.setId(elem.getId());
                                           item.setDisplayName(elem.getDisplayName());
                                           item.setNestedItemIds(elem.getDataElementGroups()
                                                                     .parallelStream()
                                                                     .map(DataElementGroupsItem::getId)
                                                                     .collect(Collectors.toList()));
                                           return item;
                                       }).collect(Collectors.toList()));
            return items;
        }
        return null;
    }

    @Override
    public Items toItems(DataElementGroups dataElementGroups)
    {
        if (dataElementGroups != null && dataElementGroups.getDataElementGroups() != null
                && dataElementGroups.getDataElementGroups().size() > 0)
        {
            Items items = new Items();
            items.setMeta(getMeta(ItemType.DATA_ELEMENT_GROUPS, dataElementGroups.getDataElementGroups().size()));
            items.setItems(dataElementGroups.getDataElementGroups()
                                            .parallelStream()
                                            .map(elem ->
                                            {
                                                Item item = new Item();
                                                item.setId(elem.getId());
                                                item.setDisplayName(elem.getDisplayName());
                                                item.setNestedItemIds(elem.getDataElements()
                                                                          .parallelStream()
                                                                          .map(DataElementsItem::getId)
                                                                          .collect(Collectors.toList()));
                                                return item;
                                            }).collect(Collectors.toList()));
            return items;
        }
        return null;
    }

    private Meta getMeta(ItemType main, Integer count)
    {
        Meta meta = new Meta();
        switch (main)
        {
            case DATA_ELEMENTS:
                meta.setItemType(ItemType.DATA_ELEMENTS);
                meta.setNestedItemType(ItemType.DATA_ELEMENT_GROUPS);
                break;
            case DATA_ELEMENT_GROUPS:
                meta.setItemType(ItemType.DATA_ELEMENT_GROUPS);
                meta.setNestedItemType(ItemType.DATA_ELEMENTS);
                break;
        }
        meta.setCount(count);
        return meta;
    }
}
