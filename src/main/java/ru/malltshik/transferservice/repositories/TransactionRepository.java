package ru.malltshik.transferservice.repositories;

import ru.malltshik.transferservice.models.Transaction;

import java.util.List;

public interface TransactionRepository {

    List<Transaction> findTransactions();

    Transaction save(Transaction transaction);
}
