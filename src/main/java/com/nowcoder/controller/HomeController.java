package com.nowcoder.controller;

import com.nowcoder.model.Question;
import com.nowcoder.pojo.VoObject;
import com.nowcoder.service.QuestionService;
import com.nowcoder.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.ArrayList;
import java.util.List;


@Controller
public class HomeController {
    private static final Logger logger= LoggerFactory.getLogger(HomeController.class);

    @Autowired
    QuestionService questionService;
    @Autowired
    UserService userService;

    @RequestMapping(path = {"/user/{userId}"}, method = {RequestMethod.GET})
    public String userIndex(Model model,@PathVariable("userId") int userId) {
        model.addAttribute("vos",getQuestions(userId,0,10));
       return  "index";
    }

    @RequestMapping(path = {"/", "/index"}, method = {RequestMethod.GET})
    public String index(Model model) {
        model.addAttribute("vos",getQuestions(0,0,10));
        return  "index";
    }
    /**
     * 获取问题
     * @param UserId
     * @param offset
     * @param limit
     * @return
     */
    public List<VoObject> getQuestions(int UserId,int offset,int limit){
        List<VoObject> list=new ArrayList<VoObject>();
        List<Question> questionList= questionService.findLastestQuestions(UserId,offset,limit);
        for (Question question :
                questionList) {
            VoObject vo=new VoObject();
            vo.set("question",question);
            vo.set("user",userService.getUserById(question.getUserId()));
            list.add(vo);
            System.out.println(question.getTitle());
        }
        return list;
    }
}
