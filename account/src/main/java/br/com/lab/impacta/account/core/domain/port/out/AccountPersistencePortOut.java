package br.com.lab.impacta.account.core.domain.port.out;

import br.com.lab.impacta.account.core.domain.model.response.AccountDomainModelResponse;

import java.util.Optional;

public interface AccountPersistencePortOut {

    Optional<AccountDomainModelResponse> findAccount(Long accountId);

    void debitAccount(AccountDomainModelResponse account);
}
