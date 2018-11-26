package com.nowcoder.dao;


import com.nowcoder.model.LoginTicket;
import org.apache.ibatis.annotations.*;

@Mapper
public interface LoginTicketDao {
    String TABLE_NAME=" login_ticket ";
    String INSERT_FILEDS=" user_id,expired,status,ticket";
    String SELECT_FILEDS=" id, "+INSERT_FILEDS;

    @Insert({"insert into ",TABLE_NAME,"(",INSERT_FILEDS,")values(#{userId},#{expired},#{status},#{ticket})"})
    int addTicketDao(LoginTicket ticket);

    //查看此时的状态
    @Select({"select ",SELECT_FILEDS," from ",TABLE_NAME," where ticket=#{ticket}"})
    LoginTicket selectByTicket(String ticket);

    //改变状态
    @Update({"update ",TABLE_NAME," set status=#{status} where ticket=#{ticket}"})
    void updateStatus(@Param("ticket") int ticket,@Param("status") int status);
}
