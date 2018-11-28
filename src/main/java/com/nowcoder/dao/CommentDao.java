package com.nowcoder.dao;

import com.nowcoder.model.Comment;
import com.nowcoder.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentDao {

       String TABLE_NAME=" Comment ";
       String INSERT_FILEDS=" comment,user_id,entity_id,entity_type,created_date,status";
       String SELECT_FILEDS=" id, "+INSERT_FILEDS;

    @Insert({" insert into ",TABLE_NAME,"(",INSERT_FILEDS,") " +
            "values (#{Comment},#{userId},#{entityId},#{entityType},#{createdDate},#{status})"})
    int addComment(Question question);

    List<Comment> selectComment(@Param("userId") int userId,
                                         @Param("offset") int offset,
                                         @Param("limit") int limit);

    @Select({"select * from ",TABLE_NAME," where id=#{id}"})
    Comment selctCommentByid(int id);
}
