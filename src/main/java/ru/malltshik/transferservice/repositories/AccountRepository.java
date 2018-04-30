package ru.malltshik.transferservice.repositories;

import ru.malltshik.transferservice.models.Account;

import java.util.List;

public interface AccountRepository {
    List<Account> findAll();
    Account getOne(Long id);
    Account save(Account account);
    void delete(Account account);
}
