package com.nowcoder.service;

import com.nowcoder.dao.QuestionDao;
import com.nowcoder.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
    public List<Question> findLastestQuestions(int userId, int offset, int limit) {
        List<Question> list=questionDao.selectLatestQuestions( userId, offset, limit);
        return list;
    }
}
