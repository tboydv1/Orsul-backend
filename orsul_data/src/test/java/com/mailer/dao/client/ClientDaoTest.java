package com.mailer.dao.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.mailer.DataConfig;
import com.mailer.entities.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.logging.Logger;


@SpringBootTest(classes = DataConfig.class) //load context from configuration class
@Sql(scripts = {"classpath:/db/insert-client-messages-script.sql"})
class ClientDaoTest {

	//get logger
	Logger log = Logger.getLogger(getClass().getName());


	@Autowired
	private ClientDao clientDaoImpl;

	@Test
	public void contextLoads(){

		assertThat(clientDaoImpl).isNotNull();
	}

	@Test
	public void findAllClientsTest(){

		log.info("Running test for find all clients method");
		List<Client> savedClients = clientDaoImpl.findAll();

		log.info("Fetched results: ");
		assertThat(savedClients).isNotNull();

		savedClients.forEach(System.out::println);

		log.info("find all method completed successfully");

	}


	//main apps to run tests
	@SpringBootApplication
	static class TestConfiguration{
	}


	

}
