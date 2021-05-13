package com.llw.goodtrash.bean;

import org.litepal.crud.LitePalSupport;

/**
 * 历史记录实体
 *
 * @author i小灰
 */
public class History extends LitePalSupport {

    private int id;
    private String name;
    private int type;
    private int aipre;
    private String explain;
    private String contain;
    private String tip;
    private String dateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAipre() {
        return aipre;
    }

    public void setAipre(int aipre) {
        this.aipre = aipre;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getContain() {
        return contain;
    }

    public void setContain(String contain) {
        this.contain = contain;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
