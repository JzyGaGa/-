package com.nowcoder;

import com.nowcoder.dao.QuestionDao;
import com.nowcoder.dao.UserDao;
import com.nowcoder.model.Question;
import com.nowcoder.model.User;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WendaApplication.class)
public class InitDatabaseTest {
    @Autowired
    UserDao userDao;
    @Autowired
    QuestionDao questionDao;

    @Test
    public void initQuestion(){
        Random random =new Random();
//        for(int i=0;i<10;i++){
//            Question question=new Question();
//            question.setId(i);
//            question.setUserId(i);
//            question.setTittle("title"+i);
//            question.setCreatedDate(new Date());
//            questionDao.addQuestion(question);
//        }
        List<Question> list= questionDao.selectLatestQuestions(0,0,10);
        System.err.println(list.get(0));
    }
    @Test
    @Ignore
    public void initDatabase(){
        Random random =new Random();
        for(int i=0;i<10;i++){
            User user=new User();
            user.setHeadUrl(String.format("htttp"+random.nextInt(1000)));
            user.setId(i);
            user.setName(String.format("user%d",i));
            user.setSalt("");
            user.setPassword("xx");
            //userDao.addUser(user);
            userDao.updatePasword(user);
            User user1=userDao.selectById(1);
            System.out.println(user1);
        }
    }


}
