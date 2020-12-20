package org.dhis2.data_element_rest.controllers.v1;

import org.dhis2.data_element_rest.api.v1.model.DataElementsDTO;
import org.dhis2.data_element_rest.services.DhisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1")
public class DataElementsController
{
    private final DhisService dhisService;

    public DataElementsController(DhisService dhisService)
    {
        this.dhisService = dhisService;
    }

    @GetMapping("api/v1/dataElemets")
    public DataElementsDTO getDataElements() throws URISyntaxException
    {
        return dhisService.getDataElements();
    }

}
