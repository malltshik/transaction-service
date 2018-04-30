package ru.malltshik.transferservice.services;

import org.jvnet.hk2.annotations.Service;
import ru.malltshik.transferservice.models.Transaction;

import java.util.List;

public interface TransferService {

    List<Transaction> findTransactions();

    Transaction applyTransaction(Transaction transaction);

    Transaction getOne(Long id);
}
