package ru.malltshik.transferservice.controllers.handlers;

import javax.inject.Singleton;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Collections;

import static java.util.Collections.singletonMap;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * NotFoundException handler
 */
@Provider
@Singleton
public class NotFoundExceptionHandler implements ExceptionMapper<NotFoundException> {

    @Context
    private HttpHeaders headers;

    public Response toResponse(NotFoundException ex) {
        return Response.status(404).entity(
                singletonMap("errors", Collections.singletonList(ex.getMessage())))
                .type(APPLICATION_JSON).build();
    }
}
