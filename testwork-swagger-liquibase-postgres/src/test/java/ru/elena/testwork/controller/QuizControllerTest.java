package ru.elena.testwork.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import ru.elena.testwork.base.QuizService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class QuizControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    QuizService quizService;

    @Test
    public void test() {
        System.out.println("test");
    }

    @Test
    public void testHome() throws Exception {
        MediaType HTML_UTF_8 = new MediaType("text", "html", java.nio.charset.Charset.forName("UTF-8"));
        this.mvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(content().contentType(HTML_UTF_8));
    }

     @Test
    public void testAction() throws Exception {
        MediaType HTML_UTF_8 = new MediaType("text", "html", java.nio.charset.Charset.forName("UTF-8"));
        this.mvc.perform(get("/action")).andExpect(status().isOk())
                .andExpect(content().contentType(HTML_UTF_8));
    }

    @Test
    public void testByname() throws Exception {
        MediaType HTML_UTF_8 = new MediaType("text", "html", java.nio.charset.Charset.forName("UTF-8"));
        this.mvc.perform(get("/byname")).andExpect(status().isOk())
                .andExpect(content().contentType(HTML_UTF_8));
    }

    @Test
    public void testBystart() throws Exception {
        MediaType HTML_UTF_8 = new MediaType("text", "html", java.nio.charset.Charset.forName("UTF-8"));
        this.mvc.perform(get("/bystart")).andExpect(status().isOk())
                .andExpect(content().contentType(HTML_UTF_8));
    }

    @Test
    public void testByend() throws Exception {
        MediaType HTML_UTF_8 = new MediaType("text", "html", java.nio.charset.Charset.forName("UTF-8"));
        this.mvc.perform(get("/byend")).andExpect(status().isOk())
                .andExpect(content().contentType(HTML_UTF_8));
    }

    @Test
    public void testPage() throws Exception {
        MediaType HTML_UTF_8 = new MediaType("text", "html", java.nio.charset.Charset.forName("UTF-8"));
        this.mvc.perform(get("/page")).andExpect(status().isOk())
                .andExpect(content().contentType(HTML_UTF_8));
    }
}
