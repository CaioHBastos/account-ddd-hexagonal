package br.com.lab.impacta.account.service.impl;

import br.com.lab.impacta.account.handler.exception.AccountDontExistsException;
import br.com.lab.impacta.account.handler.exception.AccountWithoutBalanceException;
import br.com.lab.impacta.account.model.Account;
import br.com.lab.impacta.account.repository.AccountRepository;
import br.com.lab.impacta.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Value("${lab.account.exceptions.account-dont-exists-message}")
    private String messageExceptionAccountDontExists;

    @Value("${lab.account.exceptions.account-dont-exists-description}")
    private String descriptionExceptionAccountDontExists;

    @Value("${lab.account.exceptions.account-without-balance-message}")
    private String messageExceptionAccountWithoutBalance;

    @Value("${lab.account.exceptions.account-without-balance-description}")
    private String descriptionExceptionAccountWithoutBalance;

    @Override
    public Account findAccount(Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);

        if (account.isEmpty())
            throw new AccountDontExistsException(messageExceptionAccountDontExists,
                    descriptionExceptionAccountDontExists);

        return account.get();
    }

    @Override
    public void debitAccount(Long accountId, Double valueOfDebit) {
        Account account = this.findAccount(accountId);

        boolean debited = account.debit(valueOfDebit);

        if (!debited)
            throw new AccountWithoutBalanceException(messageExceptionAccountWithoutBalance,
                    descriptionExceptionAccountWithoutBalance);

        accountRepository.save(account);
    }
}
