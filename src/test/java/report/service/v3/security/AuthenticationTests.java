package report.service.v3.security;

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
import report.service.v3.util.JsonMapperUtil;
import report.service.v3.security.service.UserDetailsServiceImpl;

import static java.util.Collections.emptyList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static report.service.v3.constants.ResponseConstants.APPROVED;
import static report.service.v3.constants.ResponseConstants.DECLINED;
import static report.service.v3.security.SecurityConstants.LOGIN_FAIL_MESSAGE;
import static report.service.v3.security.SecurityConstants.LOGIN_PATH;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class AuthenticationTests {

    private static final String ENCRYPTED_PASSWORD = "$2a$10$e5gfGVzxUQnYebHcFoDwgO0PCPZ2xrVqfjxpDJ6gpBUo3pvPsqtxC";
    private MockMvc mockMvc;
    private LoginTestRequest request;

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
        this.request = new LoginTestRequest("merchant@test.com", "password");
    }

    @Test
    public void successfulAuthentication() throws Exception {

        User user = new User("merchant@test.com", ENCRYPTED_PASSWORD, emptyList());

        when(this.userDetailsServiceImpl.loadUserByUsername(any())).thenReturn(user);

        successExpect(JsonMapperUtil.convert(this.request));
    }

    @Test
    public void unsuccessfulAuthenticationWithNullUser() throws Exception {

        when(this.userDetailsServiceImpl.loadUserByUsername(any())).thenReturn(null);

        failureExpect(JsonMapperUtil.convert(this.request));
    }

    @Test
    public void unsuccessfulAuthenticationWithInvalidPassword() throws Exception {

        User user = new User("merchant@test.com", "invalid_pass", emptyList());

        when(this.userDetailsServiceImpl.loadUserByUsername(any())).thenReturn(user);

        failureExpect(JsonMapperUtil.convert(this.request));
    }

    @Test
    public void unsuccessfulAuthenticationWithoutParams() throws Exception {
        failureExpect("");
    }

    private void successExpect(String content) throws Exception {
        this.mockMvc.perform(post(LOGIN_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.status").value(APPROVED));
    }

    private void failureExpect(String content) throws Exception {
        this.mockMvc.perform(post(LOGIN_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.token").doesNotExist())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.message").value(LOGIN_FAIL_MESSAGE))
                .andExpect(jsonPath("$.status").value(DECLINED));
    }
}

