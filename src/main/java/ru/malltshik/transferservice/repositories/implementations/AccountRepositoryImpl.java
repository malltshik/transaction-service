package ru.malltshik.transferservice.repositories.implementations;

import ru.malltshik.transferservice.models.Account;
import ru.malltshik.transferservice.repositories.AccountRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;
import java.util.List;

public class AccountRepositoryImpl implements AccountRepository {

    @Inject
    private EntityManager em;

    @Override
    public List<Account> findAll() {
        return em.createQuery("FROM Account").getResultList();
    }

    @Override
    public Account getOne(Long id) {
        return em.find(Account.class, id);
    }

    @Override
    public Account save(Account account) {
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();
        return account;
    }

    @Override
    public void delete(Account account) {
        em.remove(account);
    }
}
