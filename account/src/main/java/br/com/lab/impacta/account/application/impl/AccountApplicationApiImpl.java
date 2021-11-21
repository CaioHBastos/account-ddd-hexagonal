package br.com.lab.impacta.account.application.impl;

import br.com.lab.impacta.account.application.AccountApplicationApi;
import br.com.lab.impacta.account.application.mapper.AccountApplicationMapper;
import br.com.lab.impacta.account.application.dto.request.DebitAccountRequest;
import br.com.lab.impacta.account.application.dto.response.AccountBalanceResponse;
import br.com.lab.impacta.account.application.dto.response.DebitAccountResponse;
import br.com.lab.impacta.account.core.domain.response.AccountDomainResponse;
import br.com.lab.impacta.account.core.port.in.AccountServicePortIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountApplicationApiImpl implements AccountApplicationApi {

    @Autowired
    private AccountServicePortIn accountService;

    @Override
    public AccountBalanceResponse balance(Long accountId) {
        AccountDomainResponse account = accountService.findAccount(accountId);

        return AccountApplicationMapper.toDtoBalance(account);
    }

    @Override
    public DebitAccountResponse debit(Long accountId, DebitAccountRequest debitAccountRequest) {
       accountService.debitAccount(accountId,debitAccountRequest.getValueOfDebit());

       return new DebitAccountResponse(true);
    }
}
