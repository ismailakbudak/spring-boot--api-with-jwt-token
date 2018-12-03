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
import static report.service.v3.constants.ResponseConstants.APPROVED;
import static report.service.v3.constants.ResponseConstants.DECLINED;
import static report.service.v3.constants.ResponseConstants.DECLINED_CODE;
import static report.service.v3.security.SecurityConstants.AUTH_REQUIRED_MESSAGE;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionsReportControllerTests {

    @Autowired
    private MockMvc mockMvc;

    private String token;

    @Before
    public void setup(){
        this.token = TokenAuthenticationUtil.createToken("merchant@dev.com");
    }

    @Test
    public void noUserGetTransactionsReportReportShouldReturnForbidden() throws Exception {
        this.mockMvc.perform(get("/api/v3/transactions/report")).andDo(print()).andExpect(status().isForbidden())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.message").value(AUTH_REQUIRED_MESSAGE))
                .andExpect(jsonPath("$.status").value(DECLINED));
    }

    @Test
    public void withUserGetTransactionsReportShouldReturnBadRequestForEmptyBody() throws Exception {
        this.mockMvc.perform(post("/api/v3/transactions/report")
                .header("Authorization", this.token)
                .content(""))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.*", hasSize(3)))
                .andExpect(jsonPath("$.code").value(DECLINED_CODE))
                .andExpect(jsonPath("$.message").value("fromDate and endDate arguments required"))
                .andExpect(jsonPath("$.status").value(DECLINED));
    }

    @Test
    public void withUserGetTransactionsReportShouldReturnBadRequestForOnlyFromDate() throws Exception {
        Map<String, Object> temp = new HashMap<String, Object>(){{
            put("fromDate", "2010-07-18");
        }};

        this.mockMvc.perform(post("/api/v3/transactions/report")
                .header("Authorization", this.token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonMapperUtil.convert(temp)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void withUserGetTransactionsReportShouldReturnBadRequestForOnlyEndDate() throws Exception {
        Map<String, Object> temp = new HashMap<String, Object>(){{
            put("endDate", "2010-07-18");
        }};

        this.mockMvc.perform(post("/api/v3/transactions/report")
                .header("Authorization", this.token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonMapperUtil.convert(temp)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void withUserGetTransactionsReportShouldReturnErrorForInvalidMerchant() throws Exception {
        Map<String, Object> temp = new HashMap<String, Object>(){{
            put("merchant", "string");
        }};

        this.mockMvc.perform(post("/api/v3/transactions/report")
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
    public void withUserGetTransactionsReportShouldReturnErrorForInvalidDateFormat() throws Exception {
        Map<String, Object> temp = new HashMap<String, Object>(){{
            put("fromDate", "string");
            put("endDate", "string");
        }};

        this.mockMvc.perform(post("/api/v3/transactions/report")
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
    public void withUserGetTransactionsReportShouldReturnForDateQuery() throws Exception {
        Map<String, Object> temp = new HashMap<String, Object>(){{
            put("fromDate", "2000-07-18");
            put("endDate", "2010-07-18");
        }};

        this.mockMvc.perform(post("/api/v3/transactions/report")
                .header("Authorization", this.token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonMapperUtil.convert(temp)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)))
                .andExpect(jsonPath("$.response").isArray())
                .andExpect(jsonPath("$.response.*", hasSize(3)));
    }

    @Test
    public void withUserGetTransactionsReportShouldReturnLimitedList() throws Exception {
        Map<String, Object> temp = new HashMap<String, Object>(){{
            put("fromDate", "2057-07-18");
            put("endDate", "2061-07-18");
        }};

        this.mockMvc.perform(post("/api/v3/transactions/report")
                .header("Authorization", this.token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonMapperUtil.convert(temp)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.*", hasSize(2)))
                .andExpect(jsonPath("$.response.[0].count").value(2))
                .andExpect(jsonPath("$.response.[0].total").value(219))
                .andExpect(jsonPath("$.response.[0].currency").value("EUR"))
                .andExpect(jsonPath("$.response.[1].count").value(2))
                .andExpect(jsonPath("$.response.[1].total").value(211))
                .andExpect(jsonPath("$.response.[1].currency").value("USD"));
    }

    @Test
    public void withUserGetTransactionsReportShouldReturnLimitedListForMerchant() throws Exception {
        Map<String, Object> temp = new HashMap<String, Object>(){{
            put("fromDate", "2057-07-18");
            put("endDate", "2061-07-18");
            put("merchant", 3);
        }};

        this.mockMvc.perform(post("/api/v3/transactions/report")
                .header("Authorization", this.token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonMapperUtil.convert(temp)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.*", hasSize(1)))
                .andExpect(jsonPath("$.response.[0].*", hasSize(3)))
                .andExpect(jsonPath("$.response.[0].count").value(1))
                .andExpect(jsonPath("$.response.[0].total").value(103))
                .andExpect(jsonPath("$.response.[0].currency").value("USD"));
    }

    @Test
    public void withUserGetTransactionsReportShouldReturnLimitedListForAcquirer() throws Exception {
        Map<String, Object> temp = new HashMap<String, Object>(){{
            put("fromDate", "2057-07-18");
            put("endDate", "2061-07-18");
            put("acquirer", 11);
        }};

        this.mockMvc.perform(post("/api/v3/transactions/report")
                .header("Authorization", this.token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonMapperUtil.convert(temp)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.*", hasSize(1)))
                .andExpect(jsonPath("$.response.[0].*", hasSize(3)))
                .andExpect(jsonPath("$.response.[0].count").value(1))
                .andExpect(jsonPath("$.response.[0].total").value(103))
                .andExpect(jsonPath("$.response.[0].currency").value("USD"));
    }

    @Test
    public void withUserGetTransactionsReportShouldReturnLimitedListForMerchantAndAcquirer() throws Exception {
        Map<String, Object> temp = new HashMap<String, Object>(){{
            put("fromDate", "2001-07-18");
            put("endDate", "2061-07-18");
            put("merchant", 3);
            put("acquirer", 7);
        }};

        this.mockMvc.perform(post("/api/v3/transactions/report")
                .header("Authorization", this.token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonMapperUtil.convert(temp)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.*", hasSize(1)))
                .andExpect(jsonPath("$.response.[0].*", hasSize(3)))
                .andExpect(jsonPath("$.response.[0].count").value(5))
                .andExpect(jsonPath("$.response.[0].total").value(535))
                .andExpect(jsonPath("$.response.[0].currency").value("TRY"));
    }
}
