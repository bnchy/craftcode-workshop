package craftcode.workshop.beer.controllers;

import craftcode.workshop.beer.config.TestSecurityConfig;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@Import(TestSecurityConfig.class)
@ActiveProfiles("test")

public class ClassificationControllerTests {
}
