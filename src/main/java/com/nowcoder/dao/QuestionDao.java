package com.nowcoder.dao;

import com.nowcoder.model.Question;
import com.nowcoder.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionDao {
       String TABLE_NAME=" question ";
       String INSERT_FILEDS=" title,content,user_id,created_date,comment_count";
       String SELECT_FILEDS=" id, "+INSERT_FILEDS;

    @Insert({" insert into ",TABLE_NAME,"(",INSERT_FILEDS,") " +
            "values (#{title},#{content},#{userId},#{createdDate},#{commentCount})"})
    int addQuestion(Question question);

    List<Question> selectLatestQuestions(@Param("userId") int userId,
                                         @Param("offset")  int offset,
                                         @Param("limit") int limit);


}
