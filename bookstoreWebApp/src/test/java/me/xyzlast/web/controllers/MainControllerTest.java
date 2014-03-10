package me.xyzlast.web.controllers;

/**
 * Created by ykyoon on 3/10/14.
 */
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;
import static org.hamcrest.core.IsNot.*;
import static org.junit.Assert.*;

import me.xyzlast.web.configs.ControllerConfiguration;
import me.xyzlast.web.configs.EmptyDomainConfiguration;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {EmptyDomainConfiguration.class, ControllerConfiguration.class })
@WebAppConfiguration
public class MainControllerTest {
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getIndex() throws Exception {
        mvc.perform(get("/main/index").param("name", "윤영권"))
                .andDo(print())
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("result"))
                .andExpect(status().isOk());
    }

    @Test(expected = org.springframework.web.util.NestedServletException.class)
    public void getIndexWithError() throws Exception {
        mvc.perform(get("/main/index"))
                .andDo(print());
    }
}
