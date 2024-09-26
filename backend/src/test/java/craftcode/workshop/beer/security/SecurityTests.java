package craftcode.workshop.beer.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class SecurityTests {

    @Autowired
    private MockMvc mvc;


    @Test
    @WithMockUser(roles = "USER")
    void shouldReturnOkWhenRoleUserAndEndpointBeers() throws Exception {
        this.mvc.perform(get("/beers"))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnOkWhenRoleAdminAndEndpointBrewery() throws Exception {
        this.mvc.perform(get("/breweries"))
                .andExpect(status().isOk());
    }
    @Test
    void shouldReturnUnauthorizedWhenNotLoggedIn() throws Exception {
        this.mvc.perform(get("/classifications"))
                .andExpect(status().isUnauthorized());
    }
}