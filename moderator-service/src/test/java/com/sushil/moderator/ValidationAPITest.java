package com.sushil.moderator;

import com.sushil.moderator.api.ValidationAPI;
import com.sushil.moderator.model.APIResponse;
import com.sushil.moderator.model.ValidationRequest;
import com.sushil.moderator.services.ValidationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit test for {@link ValidationAPI}
 *
 * @author Sushil Saha
 *
 * created on 2019-09-20
 */

@RunWith(SpringRunner.class)
@WebMvcTest(ValidationAPI.class)
public class ValidationAPITest {

    private @Autowired MockMvc mockMvc;

    private @MockBean
    ValidationService validationService;

    String requestJsonWithNull = "{ \"message\": null";

    String validateJsonInput = "{ \"message\": \"message for testing containing no black listed word.\"}";

    String jsonWithBlockedWords = "{ \"message\": \"message for testing containing blocked words\"}";

    @Test
    public void validateInput_MessageAttributeNull() throws Exception {
        given(validationService.isValidContent(any(ValidationRequest.class))).willReturn(new APIResponse(HttpStatus.BAD_REQUEST));

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/validate")
                .content(requestJsonWithNull)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void validateInput_NoBlockedWord() throws Exception {
        given(validationService.isValidContent(any(ValidationRequest.class))).willReturn(new APIResponse(HttpStatus.OK));

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/validate")
                .content(validateJsonInput)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(200));
    }

    @Test
    public void validateInput_WithBlockedWord() throws Exception {
        given(validationService.isValidContent(any(ValidationRequest.class))).willReturn(new APIResponse(HttpStatus.OK,
                "input contains blocked word(s)"));

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/validate")
                .content(jsonWithBlockedWords)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("input contains blocked word(s)"));
    }

}
