package dev.n7meless.counterservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Counter API",
        version = "1.0",
        description = "Documentation Counter-Service v1.0"),
        servers = @Server(url = "/api/counter", description = "Default Server Url"))
public class CounterServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CounterServiceApplication.class, args);
    }

}
