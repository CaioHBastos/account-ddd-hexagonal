package br.com.lab.impacta.account.core.domain.service;

import br.com.lab.impacta.account.core.domain.model.response.AccountDomainModelResponse;
import br.com.lab.impacta.account.core.domain.port.in.AccountServicePortIn;
import br.com.lab.impacta.account.core.domain.port.out.AccountPersistencePortOut;
import br.com.lab.impacta.account.handler.exception.AccountDontExistsException;
import br.com.lab.impacta.account.handler.exception.AccountWithoutBalanceException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountServicePortIn {

    private final AccountPersistencePortOut accountPersistence;

    @Value("${lab.account.exceptions.account-dont-exists-message}")
    private String messageExceptionAccountDontExists;

    @Value("${lab.account.exceptions.account-dont-exists-description}")
    private String descriptionExceptionAccountDontExists;

    @Value("${lab.account.exceptions.account-without-balance-message}")
    private String messageExceptionAccountWithoutBalance;

    @Value("${lab.account.exceptions.account-without-balance-description}")
    private String descriptionExceptionAccountWithoutBalance;

    @Override
    public AccountDomainModelResponse findAccount(Long accountId) {
        Optional<AccountDomainModelResponse> account = accountPersistence.findAccount(accountId);

        if (account.isEmpty())
            throw new AccountDontExistsException(messageExceptionAccountDontExists,
                    descriptionExceptionAccountDontExists);

        return account.get();
    }

    @Override
    public void debitAccount(Long accountId, Double valueOfDebit) {
        AccountDomainModelResponse account = this.findAccount(accountId);

        boolean debited = account.debit(valueOfDebit);

        if (!debited)
            throw new AccountWithoutBalanceException(messageExceptionAccountWithoutBalance,
                    descriptionExceptionAccountWithoutBalance);

        accountPersistence.debitAccount(account);
    }
}
