package ecorp;

import ecorp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@EnableAsync
public class MainClass {
    @Autowired
    private static MessageService messageService = new MessageService();
    public static void main(String[] args) throws Exception {
        SpringApplication.run(MainClass.class, args);
    }

}

