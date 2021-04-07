package icu.kyakya.rest.jpa.doc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * doc: https://docs.spring.io/spring-restdocs/docs/current/reference/html5
 * example:
 *      - https://github.com/spring-projects/spring-restdocs/tree/master/samples/rest-notes-spring-hateoas basic usage of Spring Rest doc
 *      - https://github.com/spring-projects/spring-restdocs/tree/master/samples/junit5     for junit 5
 */
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
public class GettingStartedDocumentation {

    @Value("${spring.data.rest.basePath}")
    String basePath;

    private MockMvc mockMvc;


    @BeforeEach
    public void setUp(WebApplicationContext context, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(documentationConfiguration(restDocumentation))
                .alwaysDo(document("{method-name}/{step}/",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .build();
    }


    @Test
    public void index() throws Exception {
        this.mockMvc.perform(get(basePath + "/").accept(MediaTypes.HAL_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("_links.people", is(notNullValue())))
                .andExpect(jsonPath("_links.address", is(notNullValue())))

        ;
    }

    @Test
    public void creatingAPerson() throws Exception {
        String personLocation = createPerson();
        MvcResult note = getPerson(personLocation);
    }



    String createPerson() throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(basePath + "/people").content(
                "{\"firstName\": \"Frodo\", \"lastName\":\"Baggins\"}")).andExpect(
                status().isCreated()).andReturn().getResponse().getHeader("Location");

    }


    MvcResult getPerson(String location) throws Exception {
        return this.mockMvc.perform(get(location))
                .andExpect(status().isOk())
                .andExpect(jsonPath("_links", is(notNullValue())))
                .andReturn();
    }
}
