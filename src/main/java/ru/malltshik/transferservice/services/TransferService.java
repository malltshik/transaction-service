package ru.malltshik.transferservice.services;

import ru.malltshik.transferservice.models.Transaction;

import java.util.List;

public interface TransferService {

    List<Transaction> findTransactions();

    Transaction applyTransaction(Transaction transaction);
}
