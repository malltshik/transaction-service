package ru.malltshik.transferservice.configuration;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import ru.malltshik.transferservice.repositories.AccountRepository;
import ru.malltshik.transferservice.repositories.TransactionRepository;
import ru.malltshik.transferservice.repositories.implementations.AccountRepositoryImpl;
import ru.malltshik.transferservice.repositories.implementations.TransactionRepositoryImpl;
import ru.malltshik.transferservice.services.TransferService;
import ru.malltshik.transferservice.services.implementations.TransferServiceImpl;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

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
        bind(Persistence.createEntityManagerFactory(production ? "db" : "dbtest").createEntityManager())
                .to(EntityManager.class);
        bind(new ConstraintViolationMapper());
    }
}
