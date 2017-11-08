package fr.cqrsaxon.eventhandler;

import fr.cqrsaxon.api.event.AccountDebitedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class AccountDebitedEventHandler {
  @Autowired
  DataSource dataSource;

  @EventHandler
  public void handleAccountDebitedEvent(AccountDebitedEvent event) {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    String accountId = event.getAccountId();
    Double balance = event.getBalance();

    String updateQuery = "UPDATE account_view SET balance = ? WHERE account_no = ?";
    jdbcTemplate.update(updateQuery, new Object[]{balance, accountId});
  }
}
