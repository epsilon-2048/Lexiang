package com.epsilon.lx.dto;

import com.epsilon.lx.entities.Commentary;

import java.util.List;

public class CommentDTO {
    private Commentary father;
    private List<Commentary> children;

    public Commentary getFather() {
        return father;
    }

    public void setFather(Commentary father) {
        this.father = father;
    }

    public List<Commentary> getChildren() {
        return children;
    }

    public void setChildren(List<Commentary> children) {
        this.children = children;
    }
}
