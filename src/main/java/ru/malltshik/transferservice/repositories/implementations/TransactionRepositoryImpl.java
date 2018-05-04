package ru.malltshik.transferservice.repositories.implementations;

import ru.malltshik.transferservice.models.Transaction;
import ru.malltshik.transferservice.repositories.TransactionRepository;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class TransactionRepositoryImpl implements TransactionRepository {

    @Inject @Named("profile")
    private String profile;

    private EntityManager em = Persistence
            .createEntityManagerFactory("production".equals(profile) ? "db" : "dbtest")
            .createEntityManager();

    @Override
    public List<Transaction> findTransactions() {
        return em.createQuery("FROM Transaction").getResultList();
    }

    @Override
    public Transaction save(Transaction transaction) {
        em.getTransaction().begin();
        em.persist(transaction);
        em.flush();
        em.getTransaction().commit();
        return transaction;
    }

    @Override
    public Transaction getOne(Long id) {
        return em.find(Transaction.class, id);
    }

}
