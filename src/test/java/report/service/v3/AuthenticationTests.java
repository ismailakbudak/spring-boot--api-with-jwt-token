package report.service.v3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import report.service.v3.helper.LoginRequest;
import report.service.v3.service.UserDetailsServiceImpl;

import static java.util.Collections.emptyList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static report.service.v3.security.SecurityConstants.LOGIN_PATH;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class AuthenticationTests {

    private static final String ENCRYPTED_PASSWORD = "$2a$10$e5gfGVzxUQnYebHcFoDwgO0PCPZ2xrVqfjxpDJ6gpBUo3pvPsqtxC";
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void successfulAuthentication() throws Exception {

        LoginRequest request = new LoginRequest("user@report.com", "password");
        User user = new User("user@report.com", ENCRYPTED_PASSWORD, emptyList());

        when(this.userDetailsServiceImpl.loadUserByUsername(any())).thenReturn(user);

        successExpect(request);
    }

    @Test
    public void unsuccessfulAuthenticationWithInvalidEmail() throws Exception {

        LoginRequest request = new LoginRequest("user@report.com", "password");

        when(this.userDetailsServiceImpl.loadUserByUsername(any())).thenReturn(null);

        failureExpect(convertContent(request));
    }

    @Test
    public void unsuccessfulAuthenticationWithInvalidPassword() throws Exception {

        LoginRequest request = new LoginRequest("user@report.com", "password");
        User user = new User("user@report.com", "invalid_pass", emptyList());

        when(this.userDetailsServiceImpl.loadUserByUsername(any())).thenReturn(user);

        failureExpect(convertContent(request));
    }

    @Test
    public void unsuccessfulAuthenticationWithoutParams() throws Exception {
        failureExpect("");
    }

    private void successExpect(LoginRequest request) throws Exception {
        this.mockMvc.perform(post(LOGIN_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertContent(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.status").value("APPROVED"));
    }

    private void failureExpect(String content) throws Exception {
        this.mockMvc.perform(post(LOGIN_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.token").doesNotExist())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.message").value("Error: Merchant ApplicationUser credentials is not valid"))
                .andExpect(jsonPath("$.status").value("DECLINED"));
    }

    private String convertContent(LoginRequest request) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(request);
    }
}

