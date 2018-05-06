package ru.malltshik.transferservice.repositories;

import ru.malltshik.transferservice.models.Transaction;

import java.util.List;

/**
 * Repository for read and write transactions data to persistence storage.
 */
public interface TransactionRepository {

    List<Transaction> findTransactions();

    Transaction save(Transaction transaction);

    Transaction getOne(Long id);
}
