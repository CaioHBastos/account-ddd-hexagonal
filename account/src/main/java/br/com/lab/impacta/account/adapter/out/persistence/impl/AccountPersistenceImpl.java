package br.com.lab.impacta.account.adapter.out.persistence.impl;

import br.com.lab.impacta.account.adapter.out.persistence.entity.Account;
import br.com.lab.impacta.account.adapter.out.persistence.mapper.AccountPersistenceMapper;
import br.com.lab.impacta.account.adapter.out.persistence.repository.AccountRepository;
import br.com.lab.impacta.account.core.domain.response.AccountDomainResponse;
import br.com.lab.impacta.account.core.port.out.AccountPersistencePortOut;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AccountPersistenceImpl implements AccountPersistencePortOut {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private Tracer tracer;

    @Override
    public Optional<AccountDomainResponse> findAccount(Long accountId) {
        Span span = tracer.buildSpan("Iniciando busca dos dados da conta no repositório de conta").start();
        Optional<Account> account = accountRepository.findById(accountId);

        span.finish();
        return AccountPersistenceMapper.toDomainResponse(account);
    }

    @Transactional
    @Override
    public void debitAccount(AccountDomainResponse accountDomainModelResponse) {
        Span span = tracer.buildSpan("Iniciando o debito do valor da conta no repositório de conta").start();
        Account account = AccountPersistenceMapper.toEntity(accountDomainModelResponse);

        span.finish();
        accountRepository.save(account);
    }
}
