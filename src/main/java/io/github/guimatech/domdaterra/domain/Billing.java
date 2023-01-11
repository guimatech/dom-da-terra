package io.github.guimatech.domdaterra.domain;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Billing implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "A Operação não pode ser vázia")
    @CsvBindByName(column = "Nº da Operação")
    private String operation;

    @NotNull(message = "O Vencimento não pode ser vázio")
    @CsvDate("d/M/yyyy")
    @CsvBindByName(column = "Data de Vencimento")
    private LocalDate expiration;

    @NotEmpty(message = "O Cliente não pode ser vázio")
    @CsvBindByName(column = "Cliente")
    private String customer;

    @CsvBindByName(column = "Instituição Finan.")
    private String financialInstitution;

    @CsvBindByName(column = "Tipo de Cédula")
    private String banknoteType;

    @CsvBindByName(column = "Valor Devido Em (R$).")
    private Double amountDue;

    @CsvBindByName(column = "Projeção de Juros")
    private Double interestProjection;

    @CsvBindByName(column = "Valor do Juros")
    private Double interestValue;

    @CsvBindByName(column = "Valor Total Aproximado")
    private Double approximateTotalAmount;

    @NotEmpty(message = "A Parcela não pode estar vázia")
    @CsvBindByName(column = "Parcela")
    private String installment;

    @CsvBindByName(column = "Status do Pagamento")
    private String statusPayment;

    @Email(message = "O Email deve ser válido.")
    @NotEmpty(message = "O Email não pode ser vázio")
    @CsvBindByName(column = "Email")
    private String email;

    @NotEmpty(message = "O Telefone não pode ser vázio")
    @CsvBindByName(column = "Telefone")
    private String phone;

    @Column(columnDefinition = "boolean default true")
    private boolean active = true;
}
