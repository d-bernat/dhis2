package org.dhis2.data_element_rest.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.UnsupportedSchemeException;
import org.dhis2.data_element_rest.api.v1.model.Items;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.ResourceAccessException;

import java.io.IOException;
import java.net.URISyntaxException;

@Slf4j
@ControllerAdvice
public class ControllerAdvisor
{
    @ResponseBody
    @ExceptionHandler(URISyntaxException.class)
    public Items handleURISyntaxException(URISyntaxException exception)
    {
        return getErrorResponse(exception, "1001");
    }

    @ResponseBody
    @ExceptionHandler(UnsupportedSchemeException.class)
    public Items handleUnsupportedSchemaException(UnsupportedSchemeException exception)
    {
        return getErrorResponse(exception, "1002");
    }

    @ResponseBody
    @ExceptionHandler(IOException.class)
    public Items handleIOException(IOException exception)
    {
        return getErrorResponse(exception, "1003");
    }

    @ResponseBody
    @ExceptionHandler(ResourceAccessException.class)
    public Items handleResourceAccessException(ResourceAccessException exception)
    {
        return getErrorResponse(exception, "1004");
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Items handleDefaultException(Exception exception)
    {
        return getErrorResponse(exception, "1009");
    }

    private Items getErrorResponse(Exception exception, String internalCode)
    {
        Items items = new Items();
        org.dhis2.data_element_rest.api.v1.model.Error error = new org.dhis2.data_element_rest.api.v1.model.Error();
        error.setCode(internalCode);
        error.setMessage(exception.getMessage());
        items.setError(error);
        return items;
    }
}
