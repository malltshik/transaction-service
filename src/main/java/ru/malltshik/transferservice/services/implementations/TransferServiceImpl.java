package ru.malltshik.transferservice.services.implementations;

import ru.malltshik.transferservice.models.Transaction;
import ru.malltshik.transferservice.repositories.TransactionRepository;
import ru.malltshik.transferservice.services.TransferService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.NotFoundException;
import java.util.List;

import static ru.malltshik.transferservice.models.enums.TransactionStatus.NEW;

public class TransferServiceImpl implements TransferService {

    @Inject
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> findTransactions() {
        return transactionRepository.findTransactions();
    }

    @Override
    public Transaction applyTransaction(Transaction transaction) {
        transaction.setStatus(NEW);
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction getOne(Long id) {
        Transaction t = transactionRepository.getOne(id);
        if (t == null) throw new NotFoundException(String.format("Transaction with ID %s not found", id));
        return t;
    }
}
