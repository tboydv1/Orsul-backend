package com.mailer.entities;

import com.mailer.DataConfig;
import com.mailer.dao.client.ClientDao;
import com.mailer.dao.message.MessageRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LOCAL_DATE_TIME;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = DataConfig.class) //load context from configuration class
@Sql(scripts = {"classpath:/db/insert-client-messages-script.sql"})
class MessageRepositoryTest {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ClientDao clientDaoImpl;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void contextLoads(){
        assertThat(messageRepository).isNotNull();
    }

    @Test
    public void saveMessageTest(){

        //create a new message
        Message newMessage = new Message();
        newMessage.setSubject("Test subject");
        newMessage.setBody("Test Body");
        messageRepository.save(newMessage);
        assertThat(newMessage.getDateSent()).hasDayOfMonth(3);
    }

    @Test
    public void save_new_sent_message_and_map_to_client(){

        //create a new message
        Message newMessage = new Message();
        newMessage.setSubject("Test subject");
        newMessage.setBody("Test Body");

//        assertThat(newMessage.getDateSent()).isNotNull();
//
//        Client testClient = clientDaoImpl.findById(3);
//
//        assertThat(testClient).isNotNull();
//        assertThat(testClient).isInstanceOf(Client.class);

        Client tempClient = new Client();
        tempClient.setFirstname("Bob");
        tempClient.setLastname("Lambuk");
        tempClient.setEmail("bob@mail.com");

        newMessage.addClient(tempClient);

        messageRepository.save(newMessage);

    }

    @SpringBootApplication
    static class MessageRepositoryTestConfig{}
}