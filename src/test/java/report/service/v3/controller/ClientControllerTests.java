/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package report.service.v3.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import report.service.v3.security.TokenAuthenticationUtil;
import report.service.v3.util.JsonMapperUtil;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static report.service.v3.constants.ResponseConstants.DECLINED;
import static report.service.v3.constants.ResponseConstants.DECLINED_CODE;
import static report.service.v3.security.SecurityConstants.AUTH_REQUIRED_MESSAGE;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTests {

    @Autowired
    private MockMvc mockMvc;

    private String token;

    @Before
    public void setup(){
        this.token = TokenAuthenticationUtil.createToken("merchant@dev.com");
    }

    @Test
    public void noUserGetClientShouldReturnForbidden() throws Exception {
        this.mockMvc.perform(get("/api/v3/client"))
                .andDo(print())
                .andExpect(status()
                .isForbidden())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.message").value(AUTH_REQUIRED_MESSAGE))
                .andExpect(jsonPath("$.status").value(DECLINED));
    }


    @Test
    public void withUserGetClientShouldReturnDefaultListForEmpty() throws Exception {
        transactionIdFailureExpect("");
    }

    @Test
    public void withUserGetClientShouldReturnDefaultListForEmptyJson() throws Exception {
        transactionIdFailureExpect("{}");
    }

    @Test
    public void withUserGetClientShouldReturnErrorForInvalidTransaction() throws Exception {
        Map<String, Object> temp = new HashMap<String, Object>(){{
            put("transactionId", null);
        }};
        transactionIdFailureExpect(JsonMapperUtil.convert(temp));
    }

    @Test
    public void withUserGetClientShouldReturnErrorForNotFoundTransaction() throws Exception {
        Map<String, Object> temp = new HashMap<String, Object>(){{
            put("transactionId", "transaction");
        }};
        this.mockMvc.perform(post("/api/v3/client")
                .header("Authorization", this.token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonMapperUtil.convert(temp)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.*", hasSize(3)))
                .andExpect(jsonPath("$.code").value(DECLINED_CODE))
                .andExpect(jsonPath("$.message").value("transaction not found"))
                .andExpect(jsonPath("$.status").value(DECLINED));
    }

    @Test
    public void withUserGetClientShouldReturnInfo() throws Exception {
        Map<String, Object> temp = new HashMap<String, Object>(){{
            put("transactionId", "transaction_10");
        }};

        this.mockMvc.perform(post("/api/v3/client")
                .header("Authorization", this.token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonMapperUtil.convert(temp)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$.customerInfo").isMap())
                .andExpect(jsonPath("$.customerInfo.id").value(10));
    }

    private void transactionIdFailureExpect(String content) throws Exception {
        this.mockMvc.perform(post("/api/v3/client")
                .header("Authorization", this.token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.*", hasSize(3)))
                .andExpect(jsonPath("$.code").value(DECLINED_CODE))
                .andExpect(jsonPath("$.message").value("transactionId arguments required"))
                .andExpect(jsonPath("$.status").value(DECLINED));
    }
}
