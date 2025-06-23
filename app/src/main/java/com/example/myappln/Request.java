package com.example.myappln;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Request {
    private PartOfNeoBank part;
    private RequestStatus status;
    private String requestText;
    private String answer;

    public Request(PartOfNeoBank part, RequestStatus status, String requestText, String answer) {
        this.part = part;
        this.status = status;
        this.requestText = requestText;
        this.answer = answer;
    }

    public PartOfNeoBank getPart() {
        return part;
    }

    public void setPart(PartOfNeoBank part) {
        this.part = part;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public String getRequestText() {
        return requestText;
    }

    public void setRequestText(String requestText) {
        this.requestText = requestText;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
