package br.com.lab.impacta.account.adapter.out.persistence.mapper;

import br.com.lab.impacta.account.adapter.out.persistence.entity.Account;
import br.com.lab.impacta.account.adapter.out.persistence.entity.Person;
import br.com.lab.impacta.account.core.domain.response.AccountDomainResponse;
import br.com.lab.impacta.account.core.domain.response.PersonDomainResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountPersistenceMapper {

    public static Optional<AccountDomainResponse> toDomainResponse(Optional<Account> account) {
        if (account.isEmpty()) {
            return Optional.empty();
        }

        AccountDomainResponse accountDomainModelResponse = new AccountDomainResponse();
        accountDomainModelResponse.setId(account.get().getId());
        accountDomainModelResponse.setNumber(account.get().getNumber());
        accountDomainModelResponse.setBalance(account.get().getBalance());
        accountDomainModelResponse.setCustomer(toDomainResponseCustomer(account.get().getCustomer()));

        return Optional.of(accountDomainModelResponse);
    }

    private static PersonDomainResponse toDomainResponseCustomer(Person person) {
        if (Objects.isNull(person)) {
            return null;
        }

        PersonDomainResponse personDomainModelResponse = new PersonDomainResponse();
        personDomainModelResponse.setId(person.getId());
        personDomainModelResponse.setName(person.getName());
        personDomainModelResponse.setDocument(person.getDocument());

        return personDomainModelResponse;
    }

    public static Account toEntity(AccountDomainResponse accountDomainModelResponse) {
        Account account = new Account();
        account.setId(accountDomainModelResponse.getId());
        account.setNumber(accountDomainModelResponse.getNumber());
        account.setBalance(accountDomainModelResponse.getBalance());
        account.setCustomer(toEntityCustomer(accountDomainModelResponse.getCustomer()));

        return account;
    }

    private static Person toEntityCustomer(PersonDomainResponse personDomainModelResponse) {
        Person person = new Person();
        person.setId(personDomainModelResponse.getId());
        person.setName(personDomainModelResponse.getName());
        person.setDocument(personDomainModelResponse.getDocument());

        return person;
    }
}
