package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

    @SpringBootApplication
    public  class SpringSecurityDocumentApplication {

        private static final Logger LOGGER =
                Logger.getLogger(app.SpringSecurityDocumentApplication.class.getName());

        public static void main(String[] args) {
            SpringApplication.run(SpringSecurityDocumentApplication.class, args);
            LOGGER.info("APP is running...");
        }
    }
