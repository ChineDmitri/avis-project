package fr.esgi.config;

import fr.esgi.decorator.FileUploaderWebDecorator;
import fr.esgi.port.decorator.FileUploaderDecorator;
import fr.esgi.port.decorator.FileUploaderSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DecoratorConfig {
    @Bean(name = "fileUploaderWeb")
    public FileUploaderDecorator fileUploaderDecorator() {
        return new FileUploaderWebDecorator(
                new FileUploaderSystem()
        );
    }
}
