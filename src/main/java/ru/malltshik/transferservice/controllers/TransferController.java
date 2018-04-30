package ru.malltshik.transferservice.controllers;

import ru.malltshik.transferservice.models.Transaction;
import ru.malltshik.transferservice.services.TransferService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class TransferController {

    @Inject
    private TransferService transferService;

    @GET
    public List<Transaction> findTransactions() {
        return transferService.findTransactions();
    }

    @POST
    public Response applyTransaction(@Valid Transaction transaction) {
        Transaction t = transferService.applyTransaction(transaction);
        return Response.ok().entity(t).build();
    }
}
