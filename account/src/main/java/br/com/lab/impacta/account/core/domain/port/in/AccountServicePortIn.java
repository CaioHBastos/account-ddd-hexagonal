package br.com.lab.impacta.account.core.domain.port.in;

import br.com.lab.impacta.account.core.domain.model.response.AccountDomainModelResponse;

public interface AccountServicePortIn {

    AccountDomainModelResponse findAccount(Long accountId);

    void debitAccount(Long accountId, Double valueOfDebit);
}
