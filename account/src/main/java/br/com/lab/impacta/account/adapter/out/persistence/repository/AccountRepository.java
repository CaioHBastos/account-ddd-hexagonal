package br.com.lab.impacta.account.adapter.out.persistence.repository;

import br.com.lab.impacta.account.adapter.out.persistence.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
