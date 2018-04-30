package ru.malltshik.transferservice;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import ru.malltshik.transferservice.configuration.BeansConfiguration;

import java.io.IOException;
import java.net.URI;

public class Main {

    public static final String BASE_URI = "http://localhost:8080/";
    public static final String TEST_URI = "http://localhost:8081/";

    public static HttpServer startServer(boolean production) {
        final ResourceConfig rc = new ResourceConfig().packages("ru.malltshik");
        rc.register(new BeansConfiguration(production));
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(production ? BASE_URI : TEST_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        startServer(true);
    }
}

