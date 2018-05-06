package ru.malltshik.transferservice.services;

import ru.malltshik.transferservice.models.Transaction;

import java.util.List;

/**
 * Service handle transaction operations and provide transaction information
 */
public interface TransferService {

    List<Transaction> findTransactions();

    Transaction applyTransaction(Transaction transaction);

    Transaction getOne(Long id);
}
