package fr.cqrsaxon.web;

import fr.cqrsaxon.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ViewController {
  @Autowired
  private EntityManager entityManager;

  @RequestMapping(value = "/view", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public List<Map<String, Double>> getAccounts(@RequestBody List<String> accountsNumber) {
    List<Map<String, Double>> accounts = new ArrayList<>();
    accountsNumber.forEach(no -> {
      Account account = entityManager.find(Account.class, no);
      Map<String, Double> accountMap = new HashMap<>();
      accountMap.put((String) account.getIdentifier(), account.getBalance());
      accounts.add(accountMap);
    });

    return accounts;
  }
}

