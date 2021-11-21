package br.com.lab.impacta.account.adapter.out.mapper;

import br.com.lab.impacta.account.adapter.out.entity.Account;
import br.com.lab.impacta.account.adapter.out.entity.Person;
import br.com.lab.impacta.account.core.domain.model.response.AccountDomainModelResponse;
import br.com.lab.impacta.account.core.domain.model.response.PersonDomainModelResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountPersistenceMapper {

    public static Optional<AccountDomainModelResponse> toDomainResponse(Optional<Account> account) {
        if (account.isEmpty()) {
            return Optional.empty();
        }

        AccountDomainModelResponse accountDomainModelResponse = new AccountDomainModelResponse();
        accountDomainModelResponse.setId(account.get().getId());
        accountDomainModelResponse.setNumber(account.get().getNumber());
        accountDomainModelResponse.setBalance(account.get().getBalance());
        accountDomainModelResponse.setCustomer(toDomainResponseCustomer(account.get().getCustomer()));

        return Optional.of(accountDomainModelResponse);
    }

    private static PersonDomainModelResponse toDomainResponseCustomer(Person person) {
        if (Objects.isNull(person)) {
            return null;
        }

        PersonDomainModelResponse personDomainModelResponse = new PersonDomainModelResponse();
        personDomainModelResponse.setId(person.getId());
        personDomainModelResponse.setName(person.getName());
        personDomainModelResponse.setDocument(person.getDocument());

        return personDomainModelResponse;
    }

    public static Account toEntity(AccountDomainModelResponse accountDomainModelResponse) {
        Account account = new Account();
        account.setId(accountDomainModelResponse.getId());
        account.setNumber(accountDomainModelResponse.getNumber());
        account.setBalance(accountDomainModelResponse.getBalance());
        account.setCustomer(toEntityCustomer(accountDomainModelResponse.getCustomer()));

        return account;
    }

    private static Person toEntityCustomer(PersonDomainModelResponse personDomainModelResponse) {
        Person person = new Person();
        person.setId(personDomainModelResponse.getId());
        person.setName(personDomainModelResponse.getName());
        person.setDocument(personDomainModelResponse.getDocument());

        return person;
    }
}
