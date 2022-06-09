package sat.recruitment.api.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import sat.recruitment.api.services.UserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ImportAutoConfiguration
@WebMvcTest(UserController.class)
public class UserControllerTest {
    public static final String BASE_URL="/api/v1/users";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void createUserWhenDataOkThenOk ()throws Exception{

        var response = mvc.perform(postMock(BASE_URL, mockJsonRequest()))
                .andReturn().getResponse();

        assertThat(HttpStatus.CREATED.equals(response.getStatus()));

    }

    private String mockJsonRequest(){
        return "{\n" +
                "    \"name\" : \"Maria\",\n" +
                "\t\"email\": \"Maria@marmol.com\",\n" +
                "\t\"address\": \"Peru 2486\",\n" +
                "\t\"phone\": \"+54911547683137\",\n" +
                "\t\"userType\": \"Normal\",\n" +
                "\t\"money\":80\n" +
                "}";
    }

    private MockHttpServletRequestBuilder postMock(String uri, String contentBody){
        return post(uri)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(contentBody);

    }


}