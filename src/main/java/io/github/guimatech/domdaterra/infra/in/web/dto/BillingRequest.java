package io.github.guimatech.domdaterra.infra.in.web.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class BillingRequest {

    private Long id;

    @NotEmpty(message = "A Operação não pode ser vázia")
    private String operation;

    @NotNull(message = "O Vencimento não pode ser vázio")
    private LocalDate expiration;

    @NotEmpty(message = "O Cliente não pode ser vázio")
    private String customer;

    private String financialInstitution;
    private String banknoteType;

    private String amountDue;
    private String interestProjection;
    private String interestValue;
    private String approximateTotalAmount;

    @NotEmpty(message = "A Parcela não pode estar vázia")
    private String installment;

    private String statusPayment;

    @Email(message = "O Email deve ser válido.")
    @NotEmpty(message = "O Email não pode ser vázio")
    private String email;

    @NotEmpty(message = "O Telefone não pode ser vázio")
    private String phone;

    private boolean active;
}
