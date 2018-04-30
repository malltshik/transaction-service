package ru.malltshik.transferservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.malltshik.transferservice.validations.annatations.ExistedEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "transaction")
@Data @AllArgsConstructor @NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull(message = "Recipient ID is required")
    @ExistedEntity(message = "Recipient account doesn't exist", target = Account.class)
    private Long recipientId;

    @NotNull(message = "Sender ID is required")
    @ExistedEntity(message = "Recipient account doesn't exist", target = Account.class)
    private Long senderId;

    @NotNull(message = "Transaction amount is required")
    private Long amount;

}
