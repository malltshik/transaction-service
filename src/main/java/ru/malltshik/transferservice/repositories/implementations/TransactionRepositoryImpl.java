package ru.malltshik.transferservice.repositories.implementations;

import ru.malltshik.transferservice.models.Transaction;
import ru.malltshik.transferservice.repositories.TransactionRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;
import java.util.List;

public class TransactionRepositoryImpl implements TransactionRepository {

    @Inject
    private EntityManager em;

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
