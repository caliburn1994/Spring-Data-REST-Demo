package icu.kyakya.rest.jpa.doc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
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
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        this.mockMvc.perform(get(basePath + "/").accept(MediaTypes.HAL_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("_links.people", is(notNullValue())))
                .andExpect(jsonPath("_links.address", is(notNullValue())));
    }

    @Test
    public void creatingANote() throws JsonProcessingException, Exception {
        String noteLocation = createPerson();
        MvcResult note = getNote(noteLocation);

//        String tagLocation = createTag();
//        getTag(tagLocation);
//
//        String taggedNoteLocation = createTaggedNote(tagLocation);
//        MvcResult taggedNote = getNote(taggedNoteLocation);
//        getTags(getLink(taggedNote, "note-tags"));
//
//        tagExistingNote(noteLocation, tagLocation);
//        getTags(getLink(note, "note-tags"));
    }



    String createPerson() throws Exception {


        return mockMvc.perform(MockMvcRequestBuilders.post(basePath + "/people").content(
                "{\"firstName\": \"Frodo\", \"lastName\":\"Baggins\"}")).andExpect(
                status().isCreated()).andReturn().getResponse().getHeader("Location");




//        Map<String, String> note = new HashMap<String, String>();
//        note.put("title", "Note creation with cURL");
//        note.put("body", "An example of how to create a note using cURL");
//
//        String noteLocation = this.mockMvc
//                .perform(
//                        post("/notes").contentType(MediaTypes.HAL_JSON).content(
//                                objectMapper.writeValueAsString(note)))
//                .andExpect(status().isCreated())
//                .andExpect(header().string("Location", notNullValue()))
//                .andReturn().getResponse().getHeader("Location");
//        return noteLocation;
    }


    MvcResult getNote(String noteLocation) throws Exception {
        return this.mockMvc.perform(get(noteLocation))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("title", is(notNullValue())))
//                .andExpect(jsonPath("body", is(notNullValue())))
                .andExpect(jsonPath("_links", is(notNullValue())))
                .andReturn();
    }
}
