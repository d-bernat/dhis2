package org.dhis2.data_element_rest.clients;

import org.dhis2.data_element_rest.domain.elementgroups.DataElementGroups;
import org.dhis2.data_element_rest.domain.elements.DataElements;
import org.dhis2.data_element_rest.factories.RestTemplateFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class DHisClientImpl implements DhisClient
{
    private final RestTemplateFactory restTemplateFactory;

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
    @Value("${dhis2.dataElementGroups.path}")
    private String dataElementGroupsPath;


    public DHisClientImpl(RestTemplateFactory restTemplateFactory)
    {
        this.restTemplateFactory = restTemplateFactory;
    }

    @Override
    public DataElements getDataElements() throws URISyntaxException
    {
        RestTemplate restTemplate = restTemplateFactory.getObject();
        if (restTemplate == null)
        {
            return null;
        }
        URI uri = new URI(schema + "://" + url + ":" + port + dataElementsPath);
        return getElements(uri, restTemplate);
    }

    @Override
    public DataElementGroups getDataElementGroups() throws URISyntaxException
    {
        RestTemplate restTemplate = restTemplateFactory.getObject();
        if (restTemplate == null)
        {
            return null;
        }
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(username, password));
        URI uri = new URI(schema + "://" + url + ":" + port + dataElementGroupsPath);
        return getGroups(uri, restTemplate);
    }

    @Cacheable(value = "dataElements", key = "#uri.path")
    public DataElements getElements(URI uri, RestTemplate restTemplate)
    {
        return restTemplate.exchange(uri, HttpMethod.GET, null, DataElements.class).getBody();
    }

    @Cacheable(value = "dataElementGroups", key = "#uri.path")
    public DataElementGroups getGroups(URI uri, RestTemplate restTemplate)
    {
        return restTemplate.exchange(uri, HttpMethod.GET, null, DataElementGroups.class).getBody();
    }
}
