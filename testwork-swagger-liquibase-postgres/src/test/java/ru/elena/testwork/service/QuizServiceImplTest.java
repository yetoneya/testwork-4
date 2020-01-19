package ru.elena.testwork.service;

import com.google.common.collect.Lists;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import org.springframework.test.context.junit4.SpringRunner;
import ru.elena.testwork.base.QuizService;
import ru.elena.testwork.domain.Question;
import ru.elena.testwork.domain.Quiz;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class QuizServiceImplTest {

    @Autowired
    private QuizService quizService;

    public QuizServiceImplTest() {
    }

    @Before
    public void setUp() {

        List<Question> questions = Lists.newArrayList(new Question(null, 1, "content", null));
        quizService.save(null, "name", "1111", "2222", true, questions);
    }

    @Test
    public void testFindByName() {
        System.out.println("findByName");
        Quiz quiz = quizService.findByName("name");
        assertEquals(quiz.getName(), "name");
    }

    @Test
    public void testFindByStartDate() {
        System.out.println("findByStartDate");
        List<Quiz> quizes = quizService.findByStartDate("1111");
        assertThat(quizes, hasSize(1));
    }

    @Test
    public void testFindByEndDate() {
        System.out.println("findByEndDate");
        List<Quiz> quizes = quizService.findByEndDate("2222");
        assertThat(quizes, hasSize(1));
    }

    @Test
    public void testFindPage() {
        System.out.println("findPage");
        List<Quiz> quizes = quizService.findPage(1, 1);
        assertThat(quizes, hasSize(1));
    }

    @Test
    public void testFindAll() {
        System.out.println("findAll");
        List<Quiz> quizes = quizService.findAll();
        assertThat(quizes, hasSize(1));
    }

    @Test
    public void testFindById() {
        System.out.println("findById");
        Quiz quiz = quizService.findById(1L);
        assertEquals(quiz.getName(), "name");
    }
}
