package icu.kyakya.rest.jpa.doc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

/**
 * doc: https://docs.spring.io/spring-restdocs/docs/current/reference/html5
 * example: https://github.com/spring-projects/spring-restdocs/tree/master/samples/rest-notes-spring-hateoas
 */
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
public class GettingStartedDocumentation {

    @Value("${spring.data.rest.basePath}")
    String basePath;

//    @Rule
//    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    @Autowired
    private ObjectMapper objectMapper;

//    @Autowired
//    private WebApplicationContext context;

    private MockMvc mockMvc;

//    @BeforeEach
//    public void setUp(RestDocumentationExtension restDocumentation) {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
//                .apply(documentationConfiguration(restDocumentation))
//                .alwaysDo(document("{method-name}/{step}/",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint())))
//                .build();
//    }

    @BeforeEach
    public void setUp(WebApplicationContext context, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(documentationConfiguration(restDocumentation)).build();
    }


    @Test
    public void index() throws Exception {

        ResultMatcher resultMatcher = jsonPath("_links.people", is(notNullValue()));

        ResultMatcher matcher = jsonPath("_links.address", is(notNullValue()));
        this.mockMvc.perform(get(basePath + "/").accept(MediaTypes.HAL_JSON))
                .andExpect(status().isOk());
//                .andExpect(resultMatcher)
//                .andExpect(matcher);
    }

}
