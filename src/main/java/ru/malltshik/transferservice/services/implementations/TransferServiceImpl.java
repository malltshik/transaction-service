package ru.malltshik.transferservice.services.implementations;

import ru.malltshik.transferservice.models.Transaction;
import ru.malltshik.transferservice.repositories.TransactionRepository;
import ru.malltshik.transferservice.services.TransferService;

import javax.inject.Inject;
import java.util.List;

public class TransferServiceImpl implements TransferService {

    @Inject
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> findTransactions() {
        return transactionRepository.findTransactions();
    }

    @Override
    public Transaction applyTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
