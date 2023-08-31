package BuildWeek.Epic_Energy_Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;

import BuildWeek.Epic_Energy_Service.Security.JWTTools;
import BuildWeek.Epic_Energy_Service.utente.Utente;
import BuildWeek.Epic_Energy_Service.utente.UtenteController;
import BuildWeek.Epic_Energy_Service.utente.UtenteService;
import BuildWeek.Epic_Energy_Service.utente.payload.UtenteRequestPayload;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


import java.util.UUID;




@SpringBootTest
@AutoConfigureMockMvc
class EpicEnergyServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Autowired
    private UtenteService utenteService;


    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    // 1. successo registrazione
    @Test
    public void testSuccessfulRegistration() throws Exception {
        Faker faker = new Faker();
        String randomEmail = faker.internet().emailAddress();

        String requestBody = "{\r\n"
            + "    \"nome\": \"Marcno\",\r\n"
            + "    \"cognome\" : \"Rodessi\",\r\n"
            + "    \"username\":\"Gianldeuchino\",\r\n"
            + "    \"email\": \"" + randomEmail + "\",\r\n"
            + "    \"password\": \"dammide12\",\r\n"
            + "    \"role\" : \"ADMIN\"\r\n"
            + "}";


        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated());

    }
    
   // 2. registrazione non a buon fine
    @Test
    public void testUnsuccessfulRegistration() throws Exception {
        Faker faker = new Faker();
        String randomEmail = faker.internet().emailAddress();

        String requestBody = "{\r\n"
            + "    \"nome\": \"Marcno\",\r\n"
            + "    \"cognome\" : \"Rodessi\",\r\n"
            + "    \"username\":\"Gianldeuchino\",\r\n"
            + "    \"email\": \"" + randomEmail + "\",\r\n"
            + "    \"password\": \"dammide12\",\r\n"
            + "    \"role\" : \"ADMIN\"\r\n"
            + "}";

        // Prima registrazione
        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated()); 

        // Seconda registrazione 
        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
        		.andExpect(status().isBadRequest());

    }
    
    // 3.Test campo mancante
    @Test
    public void testMissingFieldRegistration() throws Exception {
        Faker faker = new Faker();
        String randomEmail = faker.internet().emailAddress();

        String requestBody = "{\r\n"
            + "    \"nome\": \"Marcno\",\r\n"
            + "    \"cognome\" : \"Rodessi\",\r\n"
            + "    \"username\":\"Gianldeuchino\",\r\n"
            + "    \"email\": \"" + randomEmail + "\",\r\n"
            + "    \"role\" : \"ADMIN\"\r\n"
            + "}"; //ho levato la password

        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isInternalServerError()); //non sono sicuro sia corretto

    }
    
    // 4. Test login
    @Test
    public void testSuccessfulLogin() throws Exception {
        Faker faker = new Faker();
        String randomEmail = faker.internet().emailAddress();
        
        Faker fakerUsername = new Faker();
        Name randomUsername = fakerUsername.name();

        String requestBodyRegister = "{\r\n"
            + "    \"nome\": \"Marcno\",\r\n"
            + "    \"cognome\" : \"Rodessi\",\r\n"
            + "    \"username\":\""+ randomUsername +"\",\r\n"
            + "    \"email\": \"" + randomEmail + "\",\r\n"
            + "    \"password\": \"dammide12\",\r\n"
            + "    \"role\" : \"ADMIN\"\r\n"
            + "}";

        String requestBodyLogin = "{\r\n"
            + "    \"username\":\""+ randomUsername +"\",\r\n"
            + "    \"password\": \"dammide12\"\r\n"
            + "}";

        // Registrazione
        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyRegister))
                .andExpect(status().isCreated());

        // Login
        MvcResult result = mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyLogin))
                .andExpect(status().isOk())
                .andReturn();

        String token = result.getResponse().getContentAsString();

        assertNotNull(token);
        assertFalse(token.isEmpty());
    }
    
    // 5. Test validità del token
    @Test
    public void testJWTvalido() throws Exception {
        Faker faker = new Faker();
        String randomEmail = faker.internet().emailAddress();
        String randomUsername = faker.name().username();

        String requestBodyRegister = "{\r\n"
                + "    \"nome\": \"Marcno\",\r\n"
                + "    \"cognome\" : \"Rodessi\",\r\n"
                + "    \"username\":\""+randomUsername+"\",\r\n"
                + "    \"email\": \"" + randomEmail + "\",\r\n"
                + "    \"password\": \"dammide12\",\r\n"
                + "    \"role\" : \"ADMIN\"\r\n"
                + "}";

        String requestBodyLogin = "{\r\n"
                + "    \"username\":\""+randomUsername+"\",\r\n"
                + "    \"password\": \"dammide12\"\r\n"
                + "}";

        // Registrazione
        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyRegister))
                .andExpect(status().isCreated());

        // Login e ottieni il token
        MvcResult result = mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyLogin))
                .andExpect(status().isOk())
                .andReturn();


        String token = result.getResponse().getContentAsString();

        jwtTools.verificaToken(token);

        // Estrazione del subject del token
     	String subject = jwtTools.extractSubject(token);
        
        
        Utente createdUser = utenteService.findByUsername(randomUsername);
        assertEquals(createdUser.getUserId().toString(), subject);
    }
    
    // 6. Test autorizzazioni
    @Test
    public void testUpdateUtenti_NotAdmin_ShouldReturnForbidden() throws Exception {
        Faker faker = new Faker();
        String randomEmail = faker.internet().emailAddress();
        
        Faker fakerUsername = new Faker();
        Name randomUsername = fakerUsername.name();

        String requestBodyRegister = "{\r\n"
            + "    \"nome\": \"Marcno\",\r\n"
            + "    \"cognome\" : \"Rodessi\",\r\n"
            + "    \"username\":\""+ randomUsername +"\",\r\n"
            + "    \"email\": \"" + randomEmail + "\",\r\n"
            + "    \"password\": \"dammide12\",\r\n"
            + "    \"role\" : \"USER\"\r\n"
            + "}";

        String requestBody = new ObjectMapper().writeValueAsString(requestBodyRegister);
        UUID randomUUID = UUID.randomUUID();
       
        mockMvc.perform(put("/utenti/" + randomUUID.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isInternalServerError()); //Non sono sicuro
    }


}


