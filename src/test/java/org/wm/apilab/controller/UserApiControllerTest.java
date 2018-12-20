package org.wm.apilab.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserApiControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before(value = "")
    public void setup() {
        MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void sayWorld() throws Exception {
        this.perform(get("/userApi/mockWilliam")
                .contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    private void perform(MockHttpServletRequestBuilder contentType) {
        // TODO Auto-generated method stub

    }
}
