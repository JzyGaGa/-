package com.nowcoder.service;

import com.nowcoder.dao.QuestionDao;
import com.nowcoder.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
    @Autowired
    SensitiveService sensitiveService;
    public List<Question> findLastestQuestions(int userId, int offset, int limit) {
        List<Question> list=questionDao.selectLatestQuestions( userId, offset, limit);
        return list;
    }

    public int addQuestion(Question question) {
        //html标签过滤
        question.setTitle(HtmlUtils.htmlEscape(question.getTitle()));
        question.setContent(HtmlUtils.htmlEscape(question.getContent()));
        //敏感词过滤
        question.setTitle(sensitiveService.filter(question.getTitle()));
        question.setContent(sensitiveService.filter(question.getContent()));
        int res=questionDao.addQuestion(question)>0?question.getId():0;
        return res;
    }

    public Question selectQuestionByid(int id){
        Question question=questionDao.selctQuestionByid(id);
        return question;
    }

}
