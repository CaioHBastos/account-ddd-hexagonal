package br.com.lab.impacta.account.core.service;

import br.com.lab.impacta.account.core.domain.response.AccountDomainResponse;
import br.com.lab.impacta.account.core.port.in.AccountServicePortIn;
import br.com.lab.impacta.account.core.port.out.AccountPersistencePortOut;
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
    public AccountDomainResponse findAccount(Long accountId) {
        Optional<AccountDomainResponse> account = accountPersistence.findAccount(accountId);

        if (account.isEmpty())
            throw new AccountDontExistsException(messageExceptionAccountDontExists,
                    descriptionExceptionAccountDontExists);

        return account.get();
    }

    @Override
    public void debitAccount(Long accountId, Double valueOfDebit) {
        AccountDomainResponse account = this.findAccount(accountId);

        boolean debited = account.debit(valueOfDebit);

        if (!debited)
            throw new AccountWithoutBalanceException(messageExceptionAccountWithoutBalance,
                    descriptionExceptionAccountWithoutBalance);

        accountPersistence.debitAccount(account);
    }
}
