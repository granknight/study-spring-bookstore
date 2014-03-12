package me.xyzlast.bh.utils;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;
import static org.hamcrest.core.IsNot.*;
import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


public class OfficeCounterTest {
    private OfficeCounter officeCounter;
    @Before
    public void setUp() {
        officeCounter = new OfficeCounter();
        officeCounter.add("09:12:23 11:14:35");
        officeCounter.add("10:34:01 13:23:40");
        officeCounter.add("10:34:31 11:20:10");
    }

    @Test
    public void countTest01() {
        assertThat(officeCounter.count("11:05:20"), is(3));
    }
}
