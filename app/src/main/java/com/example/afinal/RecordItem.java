package com.example.afinal;

public class RecordItem {
    private int id;
    private String link;
    private String code;

    public RecordItem() {
        super();
        link = "";
        code = "";
    }
    public RecordItem(String link, String code) {
        super();
        this.link = link;
        this.code = code;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
}
