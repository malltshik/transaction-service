package ru.malltshik.transferservice.configuration;

import javax.inject.Singleton;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Collections;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Singleton
@Provider
public class ConstraintViolationMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(Collections.singletonMap("errors", e.getConstraintViolations().stream()
                        .map(ConstraintViolation::getMessage).collect(toList())))
                .build();
    }

}