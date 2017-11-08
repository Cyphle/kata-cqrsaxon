package fr.cqrsaxon.eventhandler;

import fr.cqrsaxon.api.event.AccountCreditedEvent;
import org.axonframework.domain.Message;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventhandling.annotation.Timestamp;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class AccountCreditedEventHandler {
  @Autowired
  DataSource dataSource;

  @EventHandler
  public void handleAccountCreditedEvent(AccountCreditedEvent event, Message eventMessage, @Timestamp DateTime moment) {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    String accountId = event.getAccountId();
    Double balance = event.getBalance();

    String updateQuery = "UPDATE account_view SET balance = ? WHERE account_no = ?";
    jdbcTemplate.update(updateQuery, new Object[]{balance, accountId});

    System.out.println("Events Handled With EventMessage " + eventMessage.toString() + " at " + moment.toString());
  }
}
