package br.com.lab.impacta.account.core.port.out;

import br.com.lab.impacta.account.core.domain.response.AccountDomainResponse;

import java.util.Optional;

public interface AccountPersistencePortOut {

    Optional<AccountDomainResponse> findAccount(Long accountId);

    void debitAccount(AccountDomainResponse account);
}
