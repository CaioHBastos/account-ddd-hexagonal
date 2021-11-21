package br.com.lab.impacta.account.adapter.out.impl;

import br.com.lab.impacta.account.adapter.out.entity.Account;
import br.com.lab.impacta.account.adapter.out.mapper.AccountPersistenceMapper;
import br.com.lab.impacta.account.adapter.out.repository.AccountRepository;
import br.com.lab.impacta.account.core.domain.model.response.AccountDomainModelResponse;
import br.com.lab.impacta.account.core.domain.port.out.AccountPersistencePortOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AccountPersistenceImpl implements AccountPersistencePortOut {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Optional<AccountDomainModelResponse> findAccount(Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        Optional<AccountDomainModelResponse> accountDomainModelResponse = AccountPersistenceMapper.toDomainResponse(account);
        return accountDomainModelResponse;
    }

    @Transactional
    @Override
    public void debitAccount(AccountDomainModelResponse accountDomainModelResponse) {
        Account account = AccountPersistenceMapper.toEntity(accountDomainModelResponse);
        accountRepository.save(account);
    }
}
