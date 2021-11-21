package br.com.lab.impacta.account.application.impl;

import br.com.lab.impacta.account.application.AccountApplicationApi;
import br.com.lab.impacta.account.application.mapper.AccountAdapter;
import br.com.lab.impacta.account.application.dto.request.DebitAccountRequest;
import br.com.lab.impacta.account.application.dto.response.AccountBalanceResponse;
import br.com.lab.impacta.account.application.dto.response.DebitAccountResponse;
import br.com.lab.impacta.account.adapter.out.entity.Account;
import br.com.lab.impacta.account.core.domain.model.response.AccountDomainModelResponse;
import br.com.lab.impacta.account.core.domain.port.in.AccountServicePortIn;
import br.com.lab.impacta.account.core.domain.port.out.AccountPersistencePortOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountApplicationApiImpl implements AccountApplicationApi {

    @Autowired
    private AccountServicePortIn accountService;

    @Override
    public AccountBalanceResponse balance(Long accountId) {
        AccountDomainModelResponse account = accountService.findAccount(accountId);

        return AccountAdapter.toDtoBalance(account);
    }

    @Override
    public DebitAccountResponse debit(Long accountId, DebitAccountRequest debitAccountRequest) {
       accountService.debitAccount(accountId,debitAccountRequest.getValueOfDebit());

       return new DebitAccountResponse(true);
    }
}
