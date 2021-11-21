package br.com.lab.impacta.account.adapter.out.persistence.impl;

import br.com.lab.impacta.account.adapter.out.persistence.entity.Account;
import br.com.lab.impacta.account.adapter.out.persistence.mapper.AccountPersistenceMapper;
import br.com.lab.impacta.account.adapter.out.persistence.repository.AccountRepository;
import br.com.lab.impacta.account.core.domain.response.AccountDomainResponse;
import br.com.lab.impacta.account.core.port.out.AccountPersistencePortOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AccountPersistenceImpl implements AccountPersistencePortOut {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Optional<AccountDomainResponse> findAccount(Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        Optional<AccountDomainResponse> accountDomainModelResponse = AccountPersistenceMapper.toDomainResponse(account);
        return accountDomainModelResponse;
    }

    @Transactional
    @Override
    public void debitAccount(AccountDomainResponse accountDomainModelResponse) {
        Account account = AccountPersistenceMapper.toEntity(accountDomainModelResponse);
        accountRepository.save(account);
    }
}
