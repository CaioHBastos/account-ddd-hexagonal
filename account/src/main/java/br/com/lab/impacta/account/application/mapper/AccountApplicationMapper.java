package br.com.lab.impacta.account.application.mapper;

import br.com.lab.impacta.account.application.dto.response.AccountBalanceResponse;
import br.com.lab.impacta.account.core.domain.response.AccountDomainResponse;

public class AccountApplicationMapper {

    public static AccountBalanceResponse toDtoBalance(AccountDomainResponse account) {
        return new AccountBalanceResponse(account.getId(), account.getBalance());
    }
}
