package utils;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class ApiLoggerDecorator implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {

        // 1. Декоруємо ЗАПИТ (додаємо логування до відправки)
        System.out.println("\n========== API REQUEST ==========");
        System.out.println("Method: " + requestSpec.getMethod());
        System.out.println("URI:    " + requestSpec.getURI());
        if (requestSpec.getBody() != null) {
            System.out.println("Body:   " + requestSpec.getBody().toString());
        }

        // 2. Виконуємо сам запит (передаємо управління далі)
        Response response = ctx.next(requestSpec, responseSpec);

        // 3. Декоруємо ВІДПОВІДЬ (додаємо логування після отримання)
        System.out.println("========== API RESPONSE =========");
        System.out.println("Status: " + response.getStatusCode());
        System.out.println("Body:   " + response.asString());
        System.out.println("=================================\n");

        return response;
    }
}