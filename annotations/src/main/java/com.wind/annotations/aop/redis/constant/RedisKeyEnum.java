package com.wind.annotations.aop.redis.constant;

/**
 * @version V1.0
 * @{DESCRIPTION} : 数据redis key 管理枚举类
 **/
public enum RedisKeyEnum {


    /**用户相关缓存 redis 索引 8*/
    USER_SESSION(10,"usersession:{0}",60 * 60 * 8,"用户登录信息"),
    /** 20200801 新增记录轨迹 **/
    WIND_POINT(8,"point:{0}:{1}",60*60,"个人轨迹"),
    ;


    private int dbIndex;//redis 库索引

    private String key;//redis key

    private String explain;//key 说明

    private int seconds;

    private RedisKeyEnum(int dbIndex, String key, int seconds, String explain){
        this.dbIndex = dbIndex;
        this.key = key;
        this.explain = explain;
        this.seconds = seconds;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getDbIndex() {
        return dbIndex;
    }

    public void setDbIndex(int dbIndex) {
        this.dbIndex = dbIndex;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

}
