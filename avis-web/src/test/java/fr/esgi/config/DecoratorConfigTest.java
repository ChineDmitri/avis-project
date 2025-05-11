package fr.esgi.config;

import fr.esgi.decorator.FileUploaderWebDecorator;
import fr.esgi.port.decorator.FileUploaderDecorator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class DecoratorConfigTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withUserConfiguration(DecoratorConfig.class);

    @Test
    void shouldProvideFileUploaderDecoratorBean() {
        contextRunner.run(context -> {
            assertThat(context).hasSingleBean(FileUploaderDecorator.class);
            assertThat(context.getBean(FileUploaderDecorator.class))
                    .isInstanceOf(FileUploaderWebDecorator.class);
        });
    }
}
