package fr.esgi.config;

import jakarta.servlet.MultipartConfigElement;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class TeleversementConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withUserConfiguration(TeleversementConfiguration.class);

    @Test
    void shouldProvideMultipartConfigElementWithCorrectLimits() {
        contextRunner.run(context -> {
            assertThat(context).hasSingleBean(MultipartConfigElement.class);

            MultipartConfigElement config = context.getBean(MultipartConfigElement.class);

            assertThat(config.getMaxFileSize()).isEqualTo(2 * 1024 * 1024L); // 2 MB
            assertThat(config.getMaxRequestSize()).isEqualTo(3 * 1024 * 1024L); // 3 MB
        });
    }
}
