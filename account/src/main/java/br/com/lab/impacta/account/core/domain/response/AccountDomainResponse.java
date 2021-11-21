package br.com.lab.impacta.account.core.domain.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDomainResponse {

    private Long id;
    private Long number;
    private PersonDomainResponse customer;
    private Double balance;

    public boolean debit(Double valueOfDebit) {
        if (this.getBalance() < valueOfDebit) {
            return false;
        }

        Double debitedAmount = this.getBalance() - valueOfDebit;

        this.setBalance(debitedAmount);

        return true;
    }
}
