package com.example.androidcode.Widgets.HintView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YSB on 2017/1/3.
 */

public class BadgeHelper {
    public static final int msg1=0;
    public static final int msg2=1;
    public static final int msg3=2;
    public int msg1Count=0;//消息1数量
    public int msg2Count=1;//消息2数量
    public int msg3Count=2;//消息3数量

    private static BadgeHelper badgeHelper;

    public static BadgeHelper getInstance()
    {
        if(badgeHelper==null)
        {
            badgeHelper=new BadgeHelper();
        }
        return badgeHelper;
    }

    /**
     * 添加消息数量
     * @param count
     * @param type
     */
    public  void addMsg(int count,int type)
    {
        switch (type)
        {
            case msg1:
                msg1Count+=count;
                break;
            case msg2:
                msg2Count+=count;
                break;
            case msg3:
                msg3Count+=count;
                break;
        }
    }

    /**
     * 移除消息
     * @param count
     * @param type
     */
    public  void removeMsg(int count,int type) {
        switch (type) {
            case msg1:
                msg1Count -= count;
                break;
            case msg2:
                msg2Count -= count;
                break;
            case msg3:
                msg3Count -= count;
                break;
        }
    }

    /**
     * 是否有一条消息
     * @return
     */
    public  boolean isHaveMessage()
    {
        return msg1Count>0||msg2Count>0||msg3Count>0;
    }

    /**
     * 是否有某一个类型的消息
     * @param type
     * @return
     */
    public  boolean isHaveTypeMessage(int type)
    {
        int count=0;
        switch (type) {
            case msg1:
                count= msg1Count;
                break;
            case msg2:
                count=msg2Count;
                break;
            case msg3:
                count=msg3Count;
                break;
        }
        return count>0;
    }

    /**
     *  设置消息状态为 已读
     * @param msgid
     */
    public  void  setMsgStateToReaded(int msgid)
    {

    }
}
