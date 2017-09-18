package org.seckill.enums;

/**
 * Created by Administrator on 2017/7/11.
 */
public enum SkillStateEnum {
    SUCCESS(1,"秒杀成功"),
    END(0,"秒杀结束"),
    REPEAT_KILL(-1,"重复秒杀"),
    INNER_ERROR(-2,"系统异常"),
    DATE_REWRITE(-3,"数据篡改");
    private int state;
    private String stateInfo;
    SkillStateEnum(int state, String info) {
        this.state = state;
        this.stateInfo = info;
    }

    public int getState() {
        return state;
    }


    public String getInfo() {
        return stateInfo;
    }
    public static SkillStateEnum stateOf(int index)
    {
        for (SkillStateEnum state : values())
        {
            if (state.getState()==index)
            {
                return state;
            }
        }
        return null;
    }
}
