package org.dhis2.data_element_rest.controllers.v1;

import org.dhis2.data_element_rest.api.v1.model.Items;
import org.dhis2.data_element_rest.services.DataElementsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1")
public class DataElementsController
{
    private final DataElementsService dataElementsService;

    public DataElementsController(DataElementsService dataElementsService)
    {
        this.dataElementsService = dataElementsService;
    }

    @GetMapping("dataElements")
    public Items getDataElements() throws URISyntaxException
    {
        return dataElementsService.getDataElementItems();
    }

    @GetMapping("dataElementGroups")
    public Items getDataElementGroups() throws URISyntaxException
    {
        return dataElementsService.getDataElementGroupItems();
    }
}
