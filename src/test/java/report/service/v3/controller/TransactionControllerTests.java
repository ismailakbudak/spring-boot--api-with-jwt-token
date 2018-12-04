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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static report.service.v3.constants.ResponseConstants.DECLINED;
import static report.service.v3.constants.ResponseConstants.DECLINED_CODE;
import static report.service.v3.security.SecurityConstants.AUTH_REQUIRED_MESSAGE;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTests {

    @Autowired
    private MockMvc mockMvc;

    private String token;

    @Before
    public void setup(){
        this.token = TokenAuthenticationUtil.createToken("merchant@dev.com");
    }

    @Test
    public void noUserGetTransactionsShouldReturnForbidden() throws Exception {
        this.mockMvc.perform(get("/api/v3/transaction/list")).andDo(print()).andExpect(status().isForbidden())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.message").value(AUTH_REQUIRED_MESSAGE))
                .andExpect(jsonPath("$.status").value(DECLINED));
    }


    @Test
    public void withUserGetTransactionsShouldReturnDefaultListForEmpty() throws Exception {
        this.mockMvc.perform(post("/api/v3/transaction/list")
                .header("Authorization", this.token)
                .content(""))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void withUserGetTransactionsShouldReturnDefaultListForEmptyJson() throws Exception {
        this.mockMvc.perform(post("/api/v3/transaction/list")
                .header("Authorization", this.token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(7)))
                .andExpect(jsonPath("$.per_page").value(30))
                .andExpect(jsonPath("$.current_page").value(1))
                .andExpect(jsonPath("$.next_page_url").value("/api/v3/transaction/list?page=2"))
                .andExpect(jsonPath("$.prev_page_url").isEmpty())
                .andExpect(jsonPath("$.from").value(1))
                .andExpect(jsonPath("$.to").value(30))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.*", hasSize(30)));
    }

    @Test
    public void withUserGetTransactionsShouldReturnSecondPageList() throws Exception {
        Map<String, Object> temp = new HashMap<String, Object>(){{
            put("page", 2);
        }};

        this.mockMvc.perform(post("/api/v3/transaction/list")
                .header("Authorization", this.token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonMapperUtil.convert(temp)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.current_page").value(2))
                .andExpect(jsonPath("$.next_page_url").value("/api/v3/transaction/list?page=3"))
                .andExpect(jsonPath("$.prev_page_url").value("/api/v3/transaction/list?page=1"))
                .andExpect(jsonPath("$.from").value(31))
                .andExpect(jsonPath("$.to").value(60));
    }

    @Test
    public void withUserGetTransactionsShouldReturnErrorForInvalidPage() throws Exception {
        Map<String, Object> temp = new HashMap<String, Object>(){{
            put("page", "string");
        }};

        this.mockMvc.perform(post("/api/v3/transaction/list")
                .header("Authorization", this.token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonMapperUtil.convert(temp)))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.*", hasSize(3)))
                .andExpect(jsonPath("$.code").value(DECLINED_CODE))
                .andExpect(jsonPath("$.message").value("Internal Server Error"))
                .andExpect(jsonPath("$.status").value(DECLINED));
    }

    @Test
    public void withUserGetTransactionsShouldReturnFromDateFilterList() throws Exception {
        Map<String, Object> temp = new HashMap<String, Object>(){{
            put("fromDate", "2000-07-18");
        }};

        this.mockMvc.perform(post("/api/v3/transaction/list")
                .header("Authorization", this.token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonMapperUtil.convert(temp)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.*", hasSize(26)));
    }

    @Test
    public void withUserGetTransactionsShouldReturnEndDateFilterList() throws Exception {
        Map<String, Object> temp = new HashMap<String, Object>(){{
            put("endDate", "1970-07-18");
        }};

        this.mockMvc.perform(post("/api/v3/transaction/list")
                .header("Authorization", this.token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonMapperUtil.convert(temp)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.*", hasSize(0)));
    }

    @Test
    public void withUserGetTransactionsShouldReturnErrorForInvalidDateFormat() throws Exception {
        Map<String, Object> temp = new HashMap<String, Object>(){{
            put("fromDate", "string");
        }};

        this.mockMvc.perform(post("/api/v3/transaction/list")
                .header("Authorization", this.token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonMapperUtil.convert(temp)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.*", hasSize(3)))
                .andExpect(jsonPath("$.code").value(DECLINED_CODE))
                .andExpect(jsonPath("$.message").value("invalid date format"))
                .andExpect(jsonPath("$.status").value(DECLINED));
    }

    @Test
    public void withUserGetTransactionsShouldReturnFromAndEndDateFilterList() throws Exception {
        Map<String, Object> temp = new HashMap<String, Object>(){{
            put("fromDate", "1950-07-18");
            put("endDate", "2010-07-18");
        }};

        this.mockMvc.perform(post("/api/v3/transaction/list")
                .header("Authorization", this.token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonMapperUtil.convert(temp)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.*", hasSize(28)));
    }
}
