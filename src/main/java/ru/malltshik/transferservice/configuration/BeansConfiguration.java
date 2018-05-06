package ru.malltshik.transferservice.configuration;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import ru.malltshik.transferservice.controllers.handlers.ConstraintViolationExeptionHandler;
import ru.malltshik.transferservice.repositories.AccountRepository;
import ru.malltshik.transferservice.repositories.TransactionRepository;
import ru.malltshik.transferservice.repositories.implementations.AccountRepositoryImpl;
import ru.malltshik.transferservice.repositories.implementations.TransactionRepositoryImpl;
import ru.malltshik.transferservice.services.TransferService;
import ru.malltshik.transferservice.services.implementations.TransferServiceImpl;

import javax.inject.Singleton;

/**
 * HK2 Injection bean configuration class
 */
public class BeansConfiguration extends AbstractBinder {

    private final boolean production;

    public BeansConfiguration(boolean production) {
        this.production = production;
    }

    @Override
    protected void configure() {
        bind(TransferServiceImpl.class).to(TransferService.class).in(Singleton.class);
        bind(TransactionRepositoryImpl.class).to(TransactionRepository.class).in(Singleton.class);
        bind(AccountRepositoryImpl.class).to(AccountRepository.class).in(Singleton.class);
        bind(new ConstraintViolationExeptionHandler());
        bind(production ? "production" : "testing").to(String.class).named("profile");
    }
}
