package com.nowcoder.controller;

import com.nowcoder.Utils.WendaUtil;
import com.nowcoder.model.HostHolder;
import com.nowcoder.model.Question;
import com.nowcoder.model.User;
import com.nowcoder.service.QuestionService;
import com.nowcoder.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller()
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @Autowired
    UserService userService;
    @Autowired
    HostHolder hostHolder;
    private  static Logger logger= LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(path = "/question/{qid}",method = {RequestMethod.GET})
    public String addQuestion(Model model, @PathVariable(value = "qid") int questionId) {
        Question question=questionService.selectQuestionByid(questionId);
        User user=userService.getUserById(question.getUserId());
        model.addAttribute("user",user);
        model.addAttribute("question",question);
        return "detail";
    }




    /**
     * 增加一个question
     * @param title
     * @param content
     * @return
     */
    @RequestMapping(path = "/question/add",method = {RequestMethod.POST})
    @ResponseBody
    public String addQuestion(@RequestParam(value = "title",required = false) String title,
                              @RequestParam(value = "content",required = false) String content){


        if(title==null||content==null){
            WendaUtil.getJSONString(0,"标题或内容不能为空");
        }
            Question question=new Question();
            question.setTitle(title);
            question.setContent(content);
            question.setCreatedDate(new Date());
            if(hostHolder.getUser()==null)
                question.setUserId(0);
            question.setUserId(hostHolder.getUser().getId());
            question.setCommentCount(0);
            question.setFollowCount(0);
       try{
            if(questionService.addQuestion(question)>0){
                //0表示成功
                return WendaUtil.getJSONString(0);
            }

        }catch (Exception e){

        }

        return WendaUtil.getJSONString(0,"失败");
    }


}
