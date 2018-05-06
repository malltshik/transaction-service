package ru.malltshik.transferservice.controllers.handlers;

import javax.inject.Singleton;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Collections;

import static java.util.stream.Collectors.toList;

/**
 * Exception handler for ConstraintViolationException
 */
@Singleton
@Provider
public class ConstraintViolationExeptionHandler implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(Collections.singletonMap("errors", e.getConstraintViolations().stream()
                        .map(ConstraintViolation::getMessage).collect(toList())))
                .build();
    }

}