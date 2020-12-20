package org.dhis2.data_element_rest.services;

import org.dhis2.data_element_rest.api.v1.mapper.TrivialMapper;
import org.dhis2.data_element_rest.api.v1.model.DataElementsDTO;
import org.dhis2.data_element_rest.domain.DataElements;
import org.dhis2.data_element_rest.factories.RestTemplateFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class DHisServiceImpl implements DhisService
{
    private final RestTemplateFactory restTemplateFactory;
    private final TrivialMapper trivialMapper;

    @Value("${dhis2.username}")
    private String username;
    @Value("${dhis2.password}")
    private String password;
    @Value("${dhis2.url}")
    private String url;
    @Value("${dhis2.schema}")
    private String schema;
    @Value("${dhis2.port}")
    private Integer port;
    @Value("${dhis2.dataElements.path}")
    private String dataElementsPath;


    public DHisServiceImpl(RestTemplateFactory restTemplateFactory, TrivialMapper trivialMapper)
    {
        this.restTemplateFactory = restTemplateFactory;
        this.trivialMapper = trivialMapper;
    }

    @Override
    public DataElementsDTO getDataElements() throws URISyntaxException
    {
        RestTemplate restTemplate = restTemplateFactory.getObject();
        if(restTemplate == null)
        {
            return null;
        }
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(username, password));
        URI uri = new URI(schema + "://" + url + ":" + port + dataElementsPath);
        ResponseEntity<DataElements> dataElementsEntity = restTemplate.exchange(uri,
                HttpMethod.GET, null, DataElements.class);
        return trivialMapper.toDataElementsDTO(dataElementsEntity.getBody());
    }
}
