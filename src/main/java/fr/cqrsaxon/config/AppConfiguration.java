package fr.cqrsaxon.config;

import fr.cqrsaxon.model.Account;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AnnotationCommandHandlerBeanPostProcessor;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.common.jpa.SimpleEntityManagerProvider;
import org.axonframework.repository.GenericJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class AppConfiguration {
  @PersistenceContext
  private EntityManager entityManager;

  @Bean
  public SimpleCommandBus commandBus() {
    return new SimpleCommandBus();
  }

  @Bean
  AnnotationCommandHandlerBeanPostProcessor annotationCommandHandlerBeanPostProcessor() {
    AnnotationCommandHandlerBeanPostProcessor handler = new AnnotationCommandHandlerBeanPostProcessor();
    handler.setCommandBus(commandBus());
    return handler;
  }

  @Bean
  public DefaultCommandGateway commandGateway() {
    return new DefaultCommandGateway(commandBus());
  }

  @Bean
  public GenericJpaRepository genericJpaRepository() {
    SimpleEntityManagerProvider entityManagerProvider = new SimpleEntityManagerProvider(entityManager);
    return new GenericJpaRepository(entityManagerProvider, Account.class);
  }
}
