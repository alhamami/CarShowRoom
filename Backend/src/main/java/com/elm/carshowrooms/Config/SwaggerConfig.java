package com.elm.carshowrooms.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Car Show Room API",
        description = "This API provides set of endpoints to manage car show rooms and their associated cars. It enables users to perform operations such as creating, retrieving, updating, and deleting car show room information, as well as managing cars within each show room. The API supports pagination, sorting, and filtering capabilities to enhance data handling, providing a flexible solution for car show room management.",
        version = "1.0"
))
public class SwaggerConfig {
}
