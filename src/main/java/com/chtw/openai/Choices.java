package com.chtw.openai;

public class Choices {
    private int index;

    private String text;

    private Messages message;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Messages getMessage() {
        return message;
    }

    public void setMessage(Messages message) {
        this.message = message;
    }

    public String getContent(){
        if (this.text != null && !this.text.equals("")){
            return this.text;
        }
        return this.getMessage().getContent();
    }

    @Override
    public String toString() {
        return "Choices{" +
                "index=" + index +
                ", text='" + text + '\'' +
                ", message=" + message +
                '}';
    }
}
