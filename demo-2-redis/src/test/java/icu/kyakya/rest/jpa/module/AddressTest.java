package icu.kyakya.rest.jpa.module;

import icu.kyakya.rest.jpa.module.address.AddressRepository;
import icu.kyakya.rest.jpa.module.person.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AddressTest {

    @Value("${spring.data.rest.basePath}")
    String basePath;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    public void deleteAllBeforeTests() throws Exception {
        addressRepository.deleteAll();
        personRepository.deleteAll();
    }

    /**
     *	curl -i  -H "Content-Type:application/json" -d \
     *     '{  "firstName" : "Frodo ",  "lastName" : "Baggins", \
     *     "address":  ["http://localhost:8080/api/v1/address/1", "http://localhost:8080/api/v1/address/2"]}' \
     *     http://localhost:8080/api/v1/people
     *
     * curl -i -H "Content-Type:application/json" -d '{ "country" : "Japan" , "city" : "Tokyo" }'  http://localhost:8080/api/v1/address
     */
    @Test
    public void shouldCreateEntity() throws Exception {
        mockMvc.perform(post(basePath + "/people").content(
                "{\"firstName\": \"Frodo\", \"lastName\":\"Baggins\"}")).andExpect(
                status().isCreated()).andExpect(
                header().string("Location", containsString("people/")));

        mockMvc.perform(post(basePath + "/address").content(
                "{ \"country\" : \"Japan\" , \"city\" : \"Tokyo\" }")).andExpect(
                status().isCreated()).andExpect(
                header().string("Location", containsString("address/")));
    }


}
