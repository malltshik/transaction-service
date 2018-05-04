package ru.malltshik.transferservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.junit.*;
import ru.malltshik.transferservice.Main;
import ru.malltshik.transferservice.configuration.BeansConfiguration;
import ru.malltshik.transferservice.models.Account;
import ru.malltshik.transferservice.models.Transaction;
import ru.malltshik.transferservice.repositories.AccountRepository;
import ru.malltshik.transferservice.repositories.implementations.AccountRepositoryImpl;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static ru.malltshik.transferservice.models.enums.TransactionStatus.COMPLETED;

public class TransferControllerTest {

    private static HttpServer server;
    private static WebTarget target;

    private Account a1;
    private Account a2;
    private Transaction t;

    @Inject
    AccountRepository accountRepository;

    @BeforeClass
    public static void setUp() throws Exception {
        server = Main.startServer(false);
        Client client = ClientBuilder.newClient();
        target = client.target(Main.TEST_URI);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        if (server != null) server.shutdownNow();
    }

    @Before
    public void before() throws Exception {

        ServiceLocator locator = ServiceLocatorUtilities.bind(new BeansConfiguration(false));
        locator.inject(this);

        a1 = accountRepository.save(new Account(null, 100L));
        a2 = accountRepository.save(new Account(null, 0L));
    }

    @After
    public void after() {
        if (a1 != null) accountRepository.delete(a1);
        if (a2 != null) accountRepository.delete(a2);
    }

    @Test
    public void applyTransaction() throws Exception {
        Transaction t = new Transaction(null, a2.getId(), a1.getId(), 100L, null, null);
        Response response = target.request().post(Entity.json(t));
        String data = response.readEntity(String.class);
        Transaction rt = new ObjectMapper().readValue(data, Transaction.class);
        assertNotNull(rt);
        assertNotNull(rt.getId());
        assertEquals(rt.getStatus(), COMPLETED);
        assertEquals(accountRepository.getOne(a2.getId()).getAmount(), a1.getAmount());
        assertEquals(accountRepository.getOne(a1.getId()).getAmount(), a2.getAmount());
        this.t = rt;

    }

    @Test
    public void findTransactions() throws Exception {
        applyTransaction();
        List<Transaction> transactions = target.request().get().readEntity(new GenericType<List<Transaction>>() {
        });
        assertEquals(transactions.isEmpty(), false);
    }

    @Test
    public void getOn() throws Exception {
        applyTransaction();
        Transaction transaction = target.path("/" + t.getId()).request().get().readEntity(Transaction.class);
        assertEquals(transaction.getId(), t.getId());
    }

}