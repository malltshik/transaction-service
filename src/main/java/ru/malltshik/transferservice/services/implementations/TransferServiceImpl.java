package ru.malltshik.transferservice.services.implementations;

import ru.malltshik.transferservice.models.Account;
import ru.malltshik.transferservice.models.Transaction;
import ru.malltshik.transferservice.repositories.AccountRepository;
import ru.malltshik.transferservice.repositories.TransactionRepository;
import ru.malltshik.transferservice.services.TransferService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;

import static ru.malltshik.transferservice.models.enums.TransactionStatus.*;

public class TransferServiceImpl implements TransferService {

    @Inject
    private TransactionRepository transactionRepository;

    @Inject
    private AccountRepository accountRepository;

    @Override
    public List<Transaction> findTransactions() {
        return transactionRepository.findTransactions();
    }

    @Override
    @Transactional
    public Transaction applyTransaction(Transaction transaction) {
        Account sender = accountRepository.getOne(transaction.getSenderId());
        Account recipient = accountRepository.getOne(transaction.getRecipientId());
        if (sender.getAmount() < transaction.getAmount()) {
            transaction.setStatus(REJECTED);
            transaction.setStatusTitle("Sender's amount less then operation's amount");
        } else {
            try {
                sender.setAmount(sender.getAmount() - transaction.getAmount());
                recipient.setAmount(recipient.getAmount() + transaction.getAmount());
                accountRepository.save(sender);
                accountRepository.save(recipient);
                transaction.setStatus(COMPLETED);
                transaction.setStatusTitle("Operation successful completed");
                transaction = transactionRepository.save(transaction);
            } catch (Exception e) {
                transaction.setStatus(FAILED);
                transaction.setStatusTitle("Operation failed");
                return transaction;
            }
        }
        return transaction;
    }

    @Override
    public Transaction getOne(Long id) {
        Transaction t = transactionRepository.getOne(id);
        if (t == null) throw new NotFoundException(String.format("Transaction with ID %s not found", id));
        return t;
    }
}
