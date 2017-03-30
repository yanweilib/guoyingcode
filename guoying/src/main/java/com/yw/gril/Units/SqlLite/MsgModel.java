package com.yw.gril.Units.SqlLite;

import android.text.format.DateUtils;

import com.yw.gril.Units.DateTimeUtil;

/**
 * Created by YSB on 2017/1/16.
 */

public class MsgModel {
    private int id;
    private int m_id;
    private String title;
    private String Content;
    private String type;
    private Long createtime;
    private Long upLongtime;

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatetime() {
        return  DateTimeUtil.GetStringFromLong(createtime);
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public String getUpLongtime() {
        return  DateTimeUtil.GetStringFromLong(upLongtime);
    }

    public void setUpLongtime(Long upLongtime) {
        this.upLongtime = upLongtime;
    }
}
