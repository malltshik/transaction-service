package ru.malltshik.transferservice.repositories.implementations;

import ru.malltshik.transferservice.models.Account;
import ru.malltshik.transferservice.repositories.AccountRepository;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class AccountRepositoryImpl implements AccountRepository {


    @Inject
    @Named("profile")
    private String profile;

    private EntityManager em = Persistence
            .createEntityManagerFactory("production".equals(profile) ? "db" : "dbtest")
            .createEntityManager();

    @Override
    public List<Account> findAll() {
        return em.createQuery("FROM Account").getResultList();
    }

    @Override
    public Account getOne(Long id) {
        if (id == null) return null;
        return em.find(Account.class, id);
    }

    @Override
    public Account save(Account account) {
        em.getTransaction().begin();
        if (getOne(account.getId()) != null) {
            em.merge(account);
        } else {
            em.persist(account);
        }
        em.flush();
        em.getTransaction().commit();
        return account;
    }

    @Override
    public void delete(Account account) {
        em.remove(account);
    }
}
