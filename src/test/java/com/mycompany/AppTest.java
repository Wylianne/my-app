package com.mycompany;

import org.junit.Test;

/**
 * @author jooby generator
 */
public class AppTest extends BaseTest {

@Test
    public void index() throws Exception {
        server.get("/")
            .expect(200)
            .header("Content-Type", "text/html;charset=UTF-8");
    }


    @Test
    public void consulta() throws Exception {
        server.get("/todos")
            .expect(200)
            .header("Content-Type", "text/html;charset=UTF-8");
    }

    @Test
    public void insert() throws Exception {
        String json = "{\n" +
            "    \"name\": \"Wylianne\",\n" +
            "    \"phone\": \"57657687\"\n" +
        "}";

        server.post("/todos")
            .body(json, "application/json")
            .expect(200);
    }

}
