package br.com.lab.impacta.account.application.mapper;

import br.com.lab.impacta.account.application.dto.response.AccountBalanceResponse;
import br.com.lab.impacta.account.core.domain.model.response.AccountDomainModelResponse;

public class AccountAdapter {

    public static AccountBalanceResponse toDtoBalance(AccountDomainModelResponse account) {
        return new AccountBalanceResponse(account.getId(), account.getBalance());
    }
}
