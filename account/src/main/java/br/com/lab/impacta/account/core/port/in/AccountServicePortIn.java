package br.com.lab.impacta.account.core.port.in;

import br.com.lab.impacta.account.core.domain.response.AccountDomainResponse;

public interface AccountServicePortIn {

    AccountDomainResponse findAccount(Long accountId);

    void debitAccount(Long accountId, Double valueOfDebit);
}
