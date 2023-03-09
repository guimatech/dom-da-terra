package io.github.guimatech.domdaterra.domain.billing;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Table
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Billing implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "A Operação não pode ser vázia")
    private String operation;

    @NotNull(message = "O Vencimento não pode ser vázio")
    private LocalDate expiration;

    @NotEmpty(message = "O Cliente não pode ser vázio")
    private String customer;

    private String financialInstitution;

    private String banknoteType;

    private Double amountDue;

    private Double interestProjection;

    private Double interestValue;

    private Double approximateTotalAmount;

    @NotEmpty(message = "A Parcela não pode estar vázia")
    private String installment;

    private String statusPayment;

    @Email(message = "O Email deve ser válido.")
    @NotEmpty(message = "O Email não pode ser vázio")
    private String email;

    @NotEmpty(message = "O Telefone não pode ser vázio")
    private String phone;

    @Column(columnDefinition = "boolean default true")
    private boolean active = true;
}
