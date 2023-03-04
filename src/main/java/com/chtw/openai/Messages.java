package com.chtw.openai;

public class Messages {
    private String role;

    private String content;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String messages) {
        this.content = messages;
    }


    public Messages(String role, String messages) {
        this.role = role;
        this.content = messages;
    }

    @Override
    public String toString() {
        return "Messages{" +
                "role='" + role + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
