package com.nowcoder.model;

public class Comment {
    private Integer Id;
    private Integer userId;
    private Integer EntityId;
    private Integer EntityType;
    private  String Content;
    private Integer Status;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEntityId() {
        return EntityId;
    }

    public void setEntityId(Integer entityId) {
        EntityId = entityId;
    }

    public Integer getEntityType() {
        return EntityType;
    }

    public void setEntityType(Integer entityType) {
        EntityType = entityType;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }
}
