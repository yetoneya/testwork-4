package ru.elena.testwork.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import org.springframework.test.context.junit4.SpringRunner;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class QuizRestControllerTest {

    @Autowired
    private MockMvc mvc;

    public QuizRestControllerTest() {
    }

    @Test
    public void createQuestionTest() throws Exception {       
        this.mvc.perform(get("/create/question")
                .param("seq", "1")
                .param("content", "content"))
                .andExpect(status().isOk());
    }

    @Test
    public void createQuizTest() throws Exception {       
        this.mvc.perform(get("/create/quiz")
                .param("name", "name")
                .param("start", "11")
                .param("end", "12")
                .param("active", "true"))
                .andExpect(status().isOk());            
    }

    @Test
    public void deleteQuizTest() throws Exception {       
        this.mvc.perform(get("/delete")
                .param("id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void showQuizTest() throws Exception {
        this.mvc.perform(get("/show")
                .param("id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void saveQuestionTest() throws Exception {
        this.mvc.perform(get("/update/question")
                .param("id", "1")
                .param("content", "content"))
                .andExpect(status().isOk());
    }

    @Test
    public void saveQuizTest() throws Exception {
        this.mvc.perform(get("/update/quiz")
                .param("name", "name")
                .param("start", "1")
                .param("end", "2")
                .param("active", "true"))
                .andExpect(status().isOk());
    }
}
