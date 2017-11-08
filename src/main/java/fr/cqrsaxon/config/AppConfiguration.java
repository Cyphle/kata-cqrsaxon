package fr.cqrsaxon.config;

import fr.cqrsaxon.model.Account;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AnnotationCommandHandlerBeanPostProcessor;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.common.jpa.SimpleEntityManagerProvider;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventhandling.annotation.AnnotationEventListenerBeanPostProcessor;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.fs.FileSystemEventStore;
import org.axonframework.eventstore.fs.SimpleEventFileResolver;
import org.axonframework.repository.GenericJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;

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
    GenericJpaRepository genericJpaRepository = new GenericJpaRepository(entityManagerProvider, Account.class);
    genericJpaRepository.setEventBus(eventBus());
    return genericJpaRepository;
  }

  @Bean
  public SimpleEventBus eventBus() {
    return new SimpleEventBus();
  }

  @Bean
  AnnotationEventListenerBeanPostProcessor annotationEventListenerBeanPostProcessor() {
    AnnotationEventListenerBeanPostProcessor listener = new AnnotationEventListenerBeanPostProcessor();
    listener.setEventBus(eventBus());
    return listener;
  }

  @Bean
  public EventStore eventStore() {
    EventStore eventStore = new FileSystemEventStore(new SimpleEventFileResolver(new File("./events")));
    return eventStore;
  }

  @Bean
  public EventSourcingRepository eventSourcingRepository() {
    EventSourcingRepository eventSourcingRepository = new EventSourcingRepository(Account.class, eventStore());
    eventSourcingRepository.setEventBus(eventBus());
    return eventSourcingRepository;
  }
}
