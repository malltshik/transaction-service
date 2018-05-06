package ru.malltshik.transferservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.malltshik.transferservice.models.enums.TransactionStatus;
import ru.malltshik.transferservice.validations.annatations.ExistedAccount;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Transaction entity for store transaction operations, validate and apply them.
 */
@Entity
@Table(name = "transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull(message = "Recipient ID is required")
    @ExistedAccount(message = "Recipient account doesn't exist")
    private Long recipientId;

    @NotNull(message = "Sender ID is required")
    @ExistedAccount(message = "Sender account doesn't exist")
    private Long senderId;

    @NotNull(message = "Transaction amount is required")
    private Long amount;

    private TransactionStatus status;
    private String statusTitle;

}
