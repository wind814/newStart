package com.wind.annotations.aop.redis;

//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.gcj.config.ServletContextInitListener;
//import com.gcj.constant.DataRedisConstants;
//import com.wind.annotations.aop.redis.constant.RedisKeyEnum;
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//import java.text.MessageFormat;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.wind.annotations.aop.redis.constant.RedisKeyEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.text.MessageFormat;
import java.util.List;

/**
 * Redis工具类 方法的命名与Jedis官方命名保持一致
 */
@Component
public class RedisUtil {



//    static Logger log = LoggerFactory.getLogger(RedisUtil.class);
//
    @Autowired
    private JedisPool jedisPool;

//    private static RedisUtil instance = new RedisUtil();
//    private static RedisUtil weChatInstance = new RedisUtil("WX");
//    private static RedisUtil spuInstance = new RedisUtil("SPU");
//
    private static int TIMES_TO_TRY = 5;
//
//    public static RedisUtil getInstance() {
//      return instance;
//    }
//    public static RedisUtil getWeChatInstance() { return weChatInstance;}
//    public static RedisUtil getSpuInstance() {return spuInstance;}
//
//    private RedisUtil() {
//        init();
//    }
//    private RedisUtil(String from) {
//        if ("WX".equals(from)){
//            initWeChat();
//        }else if ("SPU".equals(from)){
//            initSPU();
//        }
//    }
//
//    private void init() {
//        System.out.println("------------初始化redis配置--------");
////        DataRedisConstants dataRedisConstants = (DataRedisConstants)ServletContextInitListener.springContext.getBean("dataRedisConstants");
//        JedisPoolConfig config = new JedisPoolConfig();
////        config.setMaxIdle(dataRedisConstants.MAXIDLE);
////        config.setMaxTotal(dataRedisConstants.MAXTOTAL);
////        config.setMaxWaitMillis(dataRedisConstants.MAXWAIT);
//
//        if (StringUtils.isBlank(dataRedisConstants.PASSWORD)) {
//            jedisPool = new JedisPool(config, dataRedisConstants.HOSTNAME, dataRedisConstants.PORT, 3000);
//        } else {
//            jedisPool = new JedisPool(config, dataRedisConstants.HOSTNAME, dataRedisConstants.PORT, 3000, dataRedisConstants.PASSWORD);
//        }
//
//    }

//    private void init(){
//        JedisPoolConfig config = new JedisPoolConfig();
//        jedisPool = new JedisPool(config, "127.0.0.1", 6379, 3000);
//    }



//
//
//    private void initWeChat() {
//        System.out.println("------------微信初始化redis配置--------");
//        DataRedisConstants dataRedisConstants = (DataRedisConstants)ServletContextInitListener.springContext.getBean("dataRedisConstants");
//        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxIdle(dataRedisConstants.WXMAXIDLE);
//        config.setMaxTotal(dataRedisConstants.WXMAXTOTAL);
//        config.setMaxWaitMillis(dataRedisConstants.WXMAXWAIT);
//
//        if (StringUtils.isBlank(dataRedisConstants.WXPASSWORD)) {
//            jedisPool = new JedisPool(config, dataRedisConstants.WXHOSTNAME, dataRedisConstants.WXPORT, 3000);
//        } else {
//            jedisPool = new JedisPool(config, dataRedisConstants.WXHOSTNAME, dataRedisConstants.WXPORT, 3000, dataRedisConstants.WXPASSWORD);
//        }
//
//    }
//
//
//    private void initSPU() {
//        System.out.println("------------材价后台redis配置--------");
//        DataRedisConstants dataRedisConstants = (DataRedisConstants)ServletContextInitListener.springContext.getBean("dataRedisConstants");
//        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxIdle(dataRedisConstants.SPU_MAXIDLE);
//        config.setMaxTotal(dataRedisConstants.SPU_MAXTOTAL);
//        config.setMaxWaitMillis(dataRedisConstants.SPU_MAXWAIT);
//
//        if (StringUtils.isBlank(dataRedisConstants.SPU_PASSWORD)) {
//            jedisPool = new JedisPool(config, dataRedisConstants.SPU_HOSTNAME, dataRedisConstants.SPU_PORT, 3000);
//        } else {
//            jedisPool = new JedisPool(config, dataRedisConstants.SPU_HOSTNAME, dataRedisConstants.SPU_PORT, 3000, dataRedisConstants.SPU_PASSWORD);
//        }
//    }
//
    /**
     * 获取jedis
     *
     * @return
     */
    public Jedis getJedis() {
        Jedis jedis = null;
        boolean broken = true;
        int times = 1;
        while (broken && times <= TIMES_TO_TRY) {
            try {
                times++;
                jedis = jedisPool.getResource();
                broken = false;
                return jedis;
            } catch (Exception e) {
                broken = true;
                e.printStackTrace();
                if (times > TIMES_TO_TRY) {
                    System.out.println("Redis get失败");
                    System.out.println(e);
                    //log.error("Redis get失败", e);
                }
            }
        }
        return jedis;
    }
//
//    /**
//     * 获取缓存标记
//     * @param redisKeyEnum
//     * @param field
//     * @return
//     */
//    public boolean getCacheFlag(RedisKeyEnum redisKeyEnum,String field){
//        String flag = hget(redisKeyEnum.getKey(),field,redisKeyEnum.getDbIndex());
//        if(StringUtils.isBlank(flag)||"false".equals(flag)){
//            return false;
//        }
//        return false;
//    }
//
//    /**
//     * 更新缓存标记
//     * @param redisKeyEnum
//     * @param field
//     */
//    public void updateCacheFlag(RedisKeyEnum redisKeyEnum,String field){
//        hset(redisKeyEnum.getKey(),field,"true",redisKeyEnum.getDbIndex());
//    }
//
//
//
//
    /**
     *  将字符串保存到redis中
     * @param redisKeyEnum
     * @param s
     * @param params
     */
    public void setString(RedisKeyEnum redisKeyEnum, String s, String... params){
        if(redisKeyEnum.getSeconds()<1){
            setString( MessageFormat.format(redisKeyEnum.getKey(),params),s,redisKeyEnum.getDbIndex());
        } else {
            setStringWithExpire( MessageFormat.format(redisKeyEnum.getKey(),params),s,redisKeyEnum.getSeconds(),redisKeyEnum.getDbIndex());
        }
    }
//
    /**
     *   * 获取存储在指定键中的值。如果键不存在，则返回null。 如果返回的值不是字符串，则返回错误
     * @param redisKeyEnum
     * @param params
     * @return 简单字符串回复。字符串值或键或null
     */
    public String getString(RedisKeyEnum redisKeyEnum,String... params) {
        return getString( MessageFormat.format(redisKeyEnum.getKey(),params),redisKeyEnum.getDbIndex());
    }
//
//    /**
//     * 查询字节数组
//     * @param redisKeyEnum
//     * @param params
//     * @return
//     */
//    public byte[] getBytes(RedisKeyEnum redisKeyEnum,String... params) {
//        byte[] bs = getBytes(MessageFormat.format(redisKeyEnum.getKey(),params),redisKeyEnum.getDbIndex());
//        return bs;
//    }
//
//
//    /**
//     * 将对象保存到redis中
//     * @param redisKeyEnum
//     * @param obj
//     * @param params
//     */
//    public void setObject(RedisKeyEnum redisKeyEnum,Object obj,String... params){
//        String s = JSON.toJSONString(obj);
//        if(redisKeyEnum.getSeconds()<1){
//            setString( MessageFormat.format(redisKeyEnum.getKey(),params),s,redisKeyEnum.getDbIndex());
//        } else {
//            setStringWithExpire( MessageFormat.format(redisKeyEnum.getKey(),params),s,redisKeyEnum.getSeconds(),redisKeyEnum.getDbIndex());
//        }
//
//    }
//
    /**
     *   * 获取存储在指定键中的值。如果键不存在，则返回null。 如果返回的值不是字符串，则返回错误
     * @param redisKeyEnum
     * @param T
     * @param params
     * @return 简单字符串回复。字符串值或键或null
     */
    public <T> T getObject(RedisKeyEnum redisKeyEnum, Class<T> T, String... params) {
        String value=  getString( MessageFormat.format(redisKeyEnum.getKey(),params),redisKeyEnum.getDbIndex());
        if(value!=null && !"".equals(value)){
            return JSON.parseObject(value,T);
        }
        return null;
    }
//
//
//    /**
//     * 将对象保存到redis中
//     * @param redisKeyEnum
//     * @param list
//     * @param params
//     */
//    public void  setList(RedisKeyEnum redisKeyEnum,List list,String... params){
//        String s = JSON.toJSONString(list);
//        if(redisKeyEnum.getSeconds()<1){
//            setString( MessageFormat.format(redisKeyEnum.getKey(),params),s,redisKeyEnum.getDbIndex());
//        } else {
//            setStringWithExpire( MessageFormat.format(redisKeyEnum.getKey(),params),s,redisKeyEnum.getSeconds(),redisKeyEnum.getDbIndex());
//        }
//
//    }
//
//
    /**
     *   * 获取存储在指定键中的值。如果键不存在，则返回null。 如果返回的值不是字符串，则返回错误
     * @param redisKeyEnum
     * @param E
     * @param params
     * @return 简单字符串回复。字符串值或键或null
     */
    public <E> List<E> getListObject(RedisKeyEnum redisKeyEnum, Class<E> E , String... params){
        String value=  getString( MessageFormat.format(redisKeyEnum.getKey(),params),redisKeyEnum.getDbIndex());
        if(value!=null && !"".equals(value)){
            return JSONArray.parseArray(value, E);
        }
        return null;
    }
//
//    /**
//     * 添加成员设置保存在key。如果成员已经存在，那么它忽略。如果键不存在，那么新的集合创建和成员被添加进去。如果没有设置储存在键的值，则返回一个错误。
//     */
//    public void addToSet(RedisKeyEnum redisKeyEnum, Set<String> set, String... params){
//        String key = MessageFormat.format(redisKeyEnum.getKey(),params);
//        if (redisKeyEnum.getSeconds() < 1) {
//            addToSet(key, set, redisKeyEnum.getDbIndex());
//        } else {
//            addToSet(key, set, redisKeyEnum.getSeconds(),redisKeyEnum.getDbIndex());
//        }
//    }
//
//    /**
//     * 移除list中跟value相等的值
//     * @param redisKeyEnum
//     * @param count
//     * @param value
//     * @param params
//     */
//    public void removeListValue(RedisKeyEnum redisKeyEnum, Long count,String value, String... params){
//        String key = MessageFormat.format(redisKeyEnum.getKey(),params);
//        removeListValue(key,count,value,redisKeyEnum.getDbIndex());
//
//    }
//
//    /**
//     * 在Redis键中设置指定的字符串值，并返回其旧值
//     * @param value
//     * @return 返回一个字符串，也就是键的旧值。 如果键不存在，则返回null
//     */
//    public String getSet(RedisKeyEnum redisKeyEnum, String value, String... params){
//        String key = MessageFormat.format(redisKeyEnum.getKey(),params);
//        return getSet(key, value ,redisKeyEnum.getDbIndex());
//    }
//
//    /**
//     * 在存储的关键值的散列设置字段。如果键不存在，新的key由哈希创建。如果字段已经存在于哈希值那么将被覆盖。
//     * @return
//     */
//    public Long hset(RedisKeyEnum redisKeyEnum, String field, String value, String... params){
//        String key = MessageFormat.format(redisKeyEnum.getKey(), params);
//        if(redisKeyEnum.getSeconds() < 1) {
//            return hset(key, field, value, redisKeyEnum.getDbIndex());
//        } else {
//            return hsetWithExpire(key, field, value, redisKeyEnum.getSeconds(), redisKeyEnum.getDbIndex());
//        }
//    }
//
//    /**
//     * 获取map中field key的数值
//     * @param redisKeyEnum
//     * @param field
//     * @param params
//     * @return
//     */
//    public String hget(RedisKeyEnum redisKeyEnum,String field, String... params){
//        String key = MessageFormat.format(redisKeyEnum.getKey(), params);
//        return hget(key,field,redisKeyEnum.getDbIndex());
//    }
//
//
//
//    /**
//     * 添加到List 在保存在key列表的头部
//     * @param redisKeyEnum
//     * @param list
//     * @param params
//     */
//    public void addList(RedisKeyEnum redisKeyEnum, List<String> list, String... params){
//        String key = MessageFormat.format(redisKeyEnum.getKey(),params);
//        int second = redisKeyEnum.getSeconds();
//        if (second < 1){
//            addList(key, list, redisKeyEnum.getDbIndex());
//        } else {
//            addList(key, list, second, redisKeyEnum.getDbIndex());
//        }
//    }
//
//    /**
//     * 添加到List 在保存在key列表的头部
//     * @param redisKeyEnum
//     * @param str
//     * @param params
//     */
//    public void addList(RedisKeyEnum redisKeyEnum, String str, String... params){
//        String key = MessageFormat.format(redisKeyEnum.getKey(),params);
//        int second = redisKeyEnum.getSeconds();
//        if (second < 1){
//            addList(key, str, redisKeyEnum.getDbIndex());
//        } else {
//            addList(key, str, second, redisKeyEnum.getDbIndex());
//        }
//    }
//
//    /**
//     *  获取List
//     */
//    public List<String> getList(RedisKeyEnum redisKeyEnum, String... params){
//        return getList(MessageFormat.format(redisKeyEnum.getKey(), params),redisKeyEnum.getDbIndex());
//    }
//    /**
//     * 删除List 删除，并返回保存列表在key的第一个元素
//     */
//    private String removeList(RedisKeyEnum redisKeyEnum, String... params){
//        return removeList(MessageFormat.format(redisKeyEnum.getKey(), params),redisKeyEnum.getDbIndex());
//    }
//
//    /**
//     * 检查List长度 将返回存储在key列表的长度。如果key不存在，它被解释为一个空列表，则返回0。当存储在关key的值不是一个列表，则会返回错误
//     */
//    public Long countList(RedisKeyEnum redisKeyEnum, String... params){
//        return countList(MessageFormat.format(redisKeyEnum.getKey(), params),redisKeyEnum.getDbIndex());
//    }
//
//    /**
//     * 弹出list中的最后一个元素
//     */
//    public String  rpop(RedisKeyEnum redisKeyEnum, String... params){
//        String key = MessageFormat.format(redisKeyEnum.getKey(),params);
//        return rpop(key,redisKeyEnum.getDbIndex());
//    }
//
//    /**
//     * 将值插入到list的头部
//     * @param redisKeyEnum
//     * @param string
//     * @param params
//     * @return
//     */
//    public Long lPushList(RedisKeyEnum redisKeyEnum, String string, String... params) {
//        String key  = MessageFormat.format(redisKeyEnum.getKey(), params);
//        if (redisKeyEnum.getSeconds() < 1){
//            return  lPushList(key,string,redisKeyEnum.getDbIndex());
//        } else {
//            return  lPushList(key,string,redisKeyEnum.getSeconds(),redisKeyEnum.getDbIndex());
//        }
//    }
//
//
//    //-------------------------------------------------------------------------------------------------------------//
//
//
//    private Long lPushList(String key , String string, int seconds,int indexDb) {
//        Long result = null;
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return null;
//        }
//        try {
//            jedis.select(indexDb);
//            result = jedis.lpush(key, string);
//            jedis.expire(key, seconds);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//        } finally {
//            returnResource(jedis);
//
//        }
//
//        return result;
//    }
//
//    private Long lPushList(String key , String string,int indexDb) {
//        Long result = null;
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return null;
//        }
//        try {
//            jedis.select(indexDb);
//            result = jedis.lpush(key, string);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//        } finally {
//            returnResource(jedis);
//
//        }
//
//        return result;
//    }
//
//
    /**
     * 用于在Redis键中设置永久性的字符串值 如果在键中设置了值，返回简单字符串回复：OK。如果值没有设置则返回 Null
     *
     * @param key
     * @param s
     * @param indexDb
     */
        private void setString(String key, String s, int indexDb) {
        Jedis jedis = getJedis();
        if(jedis==null){
            return ;
        }
        try {
            jedis.select(indexDb);
            jedis.set(key, s);

        } catch (Exception e) {
            returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }
    }
//
    /**
     * 用于在Redis键中设置有时效的字符串值
     *
     * @param key
     * @param value
     * @param seconds
     * @param indexDb
     */
    private void setStringWithExpire(String key, String value, int seconds, int indexDb) {

        Jedis jedis = getJedis();
        if(jedis==null){
            return ;
        }
        try {
            jedis.select(indexDb);
            jedis.set(key, value);
            jedis.expire(key, seconds);
        } catch (Exception e) {
            returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 获取存储在指定键中的值。如果键不存在，则返回null。 如果返回的值不是字符串，则返回错误
     *
     * @param key
     * @param indexDb
     * @return 简单字符串回复。字符串值或键或null
     */
    private String getString(String key, int indexDb) {
        boolean broken = true;
        int times = 1;
        String s = "";
        while (broken && times <= TIMES_TO_TRY) {
            times++;
            Jedis jedis = null;
            try {
                jedis = getJedis();
                jedis.select(indexDb);
                s = jedis.get(key);
                broken = false;
                return s;
            } catch (Exception e) {
                broken = true;
                returnBrokenResource(jedis);
                if (times > TIMES_TO_TRY) {
                    System.out.println("Redis get失败");
                    System.out.println(e);
//                    log.error("Redis get失败", e);
                }
            } finally {
                returnResource(jedis);
            }
        }
        return s;
    }
//
//    /**
//     * 取出字节
//     * @param key
//     * @param indexDb
//     * @return
//     */
//    private byte[] getBytes(String key, int indexDb) {
//        boolean broken = true;
//        int times = 1;
//        byte[] bs = null;
//        while (broken && times <= TIMES_TO_TRY) {
//            times++;
//            Jedis jedis = null;
//            try {
//                jedis = getJedis();
//                jedis.select(indexDb);
//                bs = jedis.get(key.getBytes());
//                broken = false;
//                return bs;
//            } catch (Exception e) {
//                broken = true;
//                returnBrokenResource(jedis);
//                if (times > TIMES_TO_TRY) {
//                    log.error("Redis get失败", e);
//                }
//            } finally {
//                returnResource(jedis);
//            }
//        }
//        return bs;
//    }
//
//    /**
//     * 添加成员设置保存在key。如果成员已经存在，那么它忽略。如果键不存在，那么新的集合创建和成员被添加进去。如果没有设置储存在键的值，则返回一个错误。
//     *
//     * @param key
//     * @param set
//     * @param indexDb
//     */
//    private void addToSet(String key, Set<String> set, int indexDb) {
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return ;
//        }
//        try {
//            jedis.select(indexDb);
//            for (String s : set) {
//                jedis.sadd(key, s);
//            }
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            log.error(e.getMessage());
//        } finally {
//            returnResource(jedis);
//        }
//    }
//
//    private void addToSet(String key, Set<String> set,int seconds, int indexDb) {
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return ;
//        }
//        try {
//            jedis.select(indexDb);
//            for (String s : set) {
//                jedis.sadd(key, s);
//            }
//            jedis.expire(key,seconds);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            log.error(e.getMessage());
//        } finally {
//            returnResource(jedis);
//        }
//    }
//
//    /**
//     * 在Redis键中设置指定的字符串值，并返回其旧值
//     *
//     * @param key
//     * @param value
//     * @param indexDb
//     * @return 返回一个字符串，也就是键的旧值。 如果键不存在，则返回null
//     */
//    private String getSet(String key, String value, int indexDb) {
//        String s = "";
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return null;
//        }
//        try {
//            jedis.select(indexDb);
//            s = jedis.getSet(key, value);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//            return null;
//        } finally {
//            returnResource(jedis);
//        }
//        return s;
//    }
//
//    /**
//     * 在存储的关键值的散列设置字段。如果键不存在，新的key由哈希创建。如果字段已经存在于哈希值那么将被覆盖。
//     *
//     * @param key
//     * @param field
//     * @param value
//     * @param indexdDb
//     * @return 1 如果字段是哈希值和一个新字段被设置。
//     * <p>
//     * 0 如果字段已经存在于哈希并且值被更新。
//     */
//    private Long hset(String key, String field, String value, int indexdDb) {
//        Long result = null;
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return null;
//        }
//        try {
//            jedis.select(indexdDb);
//            result = jedis.hset(key, field, value);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//        } finally {
//            returnResource(jedis);
//
//        }
//
//        return result;
//    }
//
//    /**
//     * 在存储的关键值的散列设置字段。如果键不存在，新的key由哈希创建。如果字段已经存在于哈希值那么将被覆盖。
//     *
//     * @param key
//     * @param field
//     * @param value
//     * @param indexdDb
//     * @return 1 如果字段是哈希值和一个新字段被设置。
//     * <p>
//     * 0 如果字段已经存在于哈希并且值被更新。
//     */
//    private Long hsetWithExpire(String key, String field, String value, int seconds, int indexdDb) {
//        Long result = null;
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return null;
//        }
//        try {
//            jedis.select(indexdDb);
//            result = jedis.hset(key, field, value);
//            jedis.expire(key, seconds);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//        } finally {
//            returnResource(jedis);
//
//        }
//
//        return result;
//    }
//
//    /**
//     * 添加到List(只新增)
//     *
//     * @param key
//     * @return
//     */
//    private boolean addList(String key, List<String> list, int indexDb) {
//        if (key == null || list == null || list.size() == 0) {
//            return false;
//        }
//        for (String value : list) {
//            addList(key, value, indexDb);
//        }
//        return true;
//    }
//
//    /**
//     * 添加到List 在保存在key列表的头部插入所有指定的值。如果key不存在，则执行推操作之前创建的空列表。当key持有的值不是列表，则返回一个错误
//     *
//     * @param key
//     * @param string
//     * @param indexDb
//     * @return 返回整数 - 推送操作后列表的长度
//     */
//    private Long addList(String key, String string, int indexDb) {
//        Long result = null;
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return null;
//        }
//        try {
//            jedis.select(indexDb);
//            result = jedis.rpush(key, string);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//        } finally {
//            returnResource(jedis);
//
//        }
//
//        return result;
//    }
//
//    /**
//     * 添加到List(只新增)
//     *
//     * @param key
//     * @return
//     */
//    private boolean addList(String key, List<String> list, int seconds, int indexDb) {
//        if (key == null || list == null || list.size() == 0) {
//            return false;
//        }
//        for (String value : list) {
//            addList(key, value, seconds, indexDb);
//        }
//        return true;
//    }
//
//    /**
//     * 添加到List 在保存在key列表的头部插入所有指定的值。如果key不存在，则执行推操作之前创建的空列表。当key持有的值不是列表，则返回一个错误
//     *
//     * @param key
//     * @param string
//     * @param indexDb
//     * @return 返回整数 - 推送操作后列表的长度
//     */
//    private Long addList(String key, String string, int seconds, int indexDb) {
//        Long result = null;
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return null;
//        }
//        try {
//            jedis.select(indexDb);
//            result = jedis.rpush(key, string);
//            jedis.expire(key, seconds);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//        } finally {
//            returnResource(jedis);
//
//        }
//
//        return result;
//    }
//
//
//    /**
//     * 获取List
//     *
//     * @param key
//     * @param indexDb
//     * @return
//     */
//    private List<String> getList(String key, int indexDb) {
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return null;
//        }
//        try {
//            jedis.select(indexDb);
//            return jedis.lrange(key, 0, -1);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//        } finally {
//            returnResource(jedis);
//        }
//        return null;
//    }
//
//    /**
//     * 删除List 删除，并返回保存列表在key的第一个元素
//     *
//     * @param key
//     * @param indexDb
//     * @return
//     */
//    private String removeList(String key, int indexDb) {
//        String result = null;
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return null;
//        }
//        try {
//            jedis.select(indexDb);
//            result = jedis.lpop(key);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//        } finally {
//            returnResource(jedis);
//        }
//        return result;
//    }
//
//    /**
//     * 检查List长度 将返回存储在key列表的长度。如果key不存在，它被解释为一个空列表，则返回0。当存储在关key的值不是一个列表，则会返回错误
//     *
//     * @param key
//     * @param indexDb
//     * @return
//     */
//    private Long countList(String key, int indexDb) {
//        Long result = null;
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return null;
//        }
//        try {
//            jedis.select(indexDb);
//            result = jedis.llen(key);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//        } finally {
//            returnResource(jedis);
//
//        }
//        return result;
//    }
//
//    /**
//     * 删除，并返回列表保存在key的最后一个元素
//     *
//     * @param key
//     * @param indexDb
//     * @return
//     */
//    private String rpop(String key, int indexDb) {
//        String result = null;
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return null;
//        }
//        try {
//            jedis.select(indexDb);
//            result = jedis.rpop(key);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//        } finally {
//            returnResource(jedis);
//
//        }
//        return result;
//    }
//
//    /**
//     * 从list中删除value
//     *
//     * @param key
//     * @param count 要删除个数
//     * @param value
//     * @return 成功删除的键的数量
//     */
//    private long removeListValue(String key, long count, String value, int indexDb) {
//        long s = 0L;
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return 0L;
//        }
//        try {
//            jedis.select(indexDb);
//            s = jedis.lrem(key, count, value);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//        } finally {
//            returnResource(jedis);
//        }
//        return s;
//    }
//
//    /**
//     * 修改list中的值
//     *
//     * @param key
//     * @param index   list的下标
//     * @param value
//     * @param indexDb
//     * @return
//     */
//    public String lset(String key, long index, String value, int indexDb) {
//        String s = null;
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return null;
//        }
//        try {
//            jedis.select(indexDb);
//            s = jedis.lset(key, index, value);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//        } finally {
//            returnResource(jedis);
//        }
//        return s;
//    }
//
//    /**
//     * 修改list中的值，并设置过期时间
//     *
//     * @param key
//     * @param index   list的下标
//     * @param value
//     * @param seconds 过期时间
//     * @param indexDb
//     * @return
//     */
//    public String lsetWithExpire(String key, long index, String value, int seconds, int indexDb) {
//        String s = null;
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return null;
//        }
//        try {
//            jedis.select(indexDb);
//            s = jedis.lset(key, index, value);
//            jedis.expire(key, seconds);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//        } finally {
//            returnResource(jedis);
//        }
//        return s;
//    }
//
    /**
     * 删除Redis中现有/存在的键
     *
     * @param key
     * @param indexDb
     * @return 成功删除的键的数量
     */
    public Long delete(String key, int indexDb) {
        long s = 0L;
        Jedis jedis = getJedis();
        if(jedis==null){
            return null;
        }
        try {
            jedis.select(indexDb);
            s = jedis.del(key);
        } catch (Exception e) {
            returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }
        return s;
    }

    /**
     * 删除Redis中现有/存在的键
     *
     * @param redisKeyEnum
     * @param params
     * @return 成功删除的键的数量
     */
    public Long delete(RedisKeyEnum redisKeyEnum,String... params) {
        return delete(MessageFormat.format(redisKeyEnum.getKey(),params),redisKeyEnum.getDbIndex());
    }
//
//    /**
//     * 删除Redis中现有/存在的键
//     *
//     * @param indexDb
//     * @param keys
//     * @return 成功删除的键的数量
//     */
//    public Long delete(Set keys,int indexDb) {
//        long s = 0L;
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return null;
//        }
//        if(keys==null || keys.size()==0){
//            return s;
//        }
//        try {
//            //Set[] 转成String[]
//            Object[] keysObj = keys.toArray();
//            String[] result = new String[keysObj.length];
//            int i=0;
//            for (Object o : keysObj) {
//                if(o instanceof String){
//                    result[i]=(String)o;
//                }
//                i++;
//            }
//
//            jedis.select(indexDb);
//            s = jedis.del(result);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//        } finally {
//            returnResource(jedis);
//        }
//        return s;
//    }
//
//    /**
//     * 获取与字段中存储的键哈希相关联的值
//     *
//     * @param key
//     * @param field
//     * @param indexDb
//     * @return 字符串值关联字段，或null当字段时不存在哈希或键不存在值
//     */
//    public String hget(String key, String field, int indexDb) {
//        String result = null;
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return null;
//        }
//        try {
//            jedis.select(indexDb);
//            result = jedis.hget(key, field);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//        } finally {
//            returnResource(jedis);
//        }
//        return result;
//    }
//
//    /**
//     * 获取存储在键的散列的所有字段和值
//     *
//     * @param key
//     * @param indexDb
//     * @return
//     */
//    public Map<String, String> hgetAll(String key, int indexDb) {
//        Map<String, String> result = null;
//        boolean broken = true;
//        int times = 1;
//        while (broken && times <= TIMES_TO_TRY) {
//            times++;
//            Jedis jedis = getJedis();
//            try {
//                jedis.select(indexDb);
//                result = jedis.hgetAll(key);
//                broken = false;
//            } catch (Exception e) {
//                e.printStackTrace();
//                broken = true;
//                returnBrokenResource(jedis);
//                if (times > TIMES_TO_TRY) {
//                    log.error("Redis get失败", e);
//                }
//            } finally {
//                returnResource(jedis);
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 检查哈希字段是否存在
//     *
//     * @param key
//     * @param field
//     * @param indexDb
//     * @return 整数，1或0。 1, 如果哈希包含字段。 0 如果哈希不包含字段，或key不存在。
//     */
//    public boolean hexist(String key, String field, int indexDb) {
//        boolean result = false;
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return result;
//        }
//        try {
//            jedis.select(indexDb);
//            result = jedis.hexists(key, field);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//        } finally {
//            returnResource(jedis);
//        }
//        return result;
//    }
//
//    /**
//     * 从存储在键散列删除指定的字段。如果没有这个哈希中存在指定的字段将被忽略。如果键不存在，它将被视为一个空的哈希与此命令将返回0
//     *
//     * @param key
//     * @param field
//     * @param indexDb
//     * @return 整数，从散列中删除的字段的数量，不包括指定的但不是现有字段。
//     */
//    public Long hdel(String key, String field, int indexDb) {
//        Long result = null;
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return null;
//        }
//        try {
//            jedis.select(indexDb);
//            result = jedis.hdel(key, field);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//        } finally {
//            returnResource(jedis);
//        }
//        return result;
//    }
//
//    /**
//     * 设置指定字段各自的值，在存储于键的散列。此命令将覆盖哈希任何现有字段。如果键不存在，新的key由哈希创建
//     *
//     * @param key
//     * @param indexDb
//     * @param hash
//     * @return
//     */
//    public String hmset(String key, int indexDb, Map<String, String> hash) {
//        String s = "";
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return null;
//        }
//        try {
//            jedis.select(indexDb);
//            s = jedis.hmset(key, hash);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//            return null;
//        } finally {
//            returnResource(jedis);
//        }
//        return s;
//    }
//
//    /**
//     * 存放Map，有有效期时间
//     *
//     * @param key
//     * @param hash
//     * @param expireSeconds
//     * @param indexDb
//     * @return
//     */
//    public String hmsetWithExpire(String key, Map<String, String> hash, int expireSeconds, int indexDb) {
//        String s = "";
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return null;
//        }
//        try {
//            jedis.select(indexDb);
//            s = jedis.hmset(key, hash);
//            jedis.expire(key, expireSeconds);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//            return null;
//        } finally {
//            returnResource(jedis);
//        }
//        return s;
//    }
//
//    /**
//     * 获取与存储在键散列指定的字段相关联的值。如果字段中哈希不存在，则null值被返回
//     *
//     * @param key
//     * @param indexDb
//     * @param fields
//     * @return 回复数组，给定字段相关联的值的列表，与在请求时它们的顺序相同。
//     */
//    public List<String> hmget(String key, int indexDb, String... fields) {
//        List<String> s = null;
//        ;
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return null;
//        }
//        try {
//            jedis.select(indexDb);
//            s = jedis.hmget(key, fields);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//            return s;
//        } finally {
//            returnResource(jedis);
//        }
//        return s;
//    }
//
//    /**
//     * 从存储在键散列删除指定的字段。如果没有这个哈希中存在指定的字段将被忽略。如果键不存在，它将被视为一个空的哈希与此命令将返回0
//     *
//     * @param key
//     * @param indexDb
//     * @param fields
//     * @return 整数，从散列中删除的字段的数量，不包括指定的但不是现有字段
//     */
//    public Long hdel(String key, int indexDb, String... fields) {
//        long s = 0L;
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return null;
//        }
//        try {
//            jedis.select(indexDb);
//            s = jedis.hdel(key, fields);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//            return s;
//        } finally {
//            returnResource(jedis);
//        }
//        return s;
//    }
//
//    /**
//     * 增加存储在字段中存储由增量键哈希的数量。如果键不存在，新的key被哈希创建。如果字段不存在，值被设置为0之前进行操作。
//     *
//     * @param key
//     * @param field
//     * @param number
//     * @param indexDb
//     * @return 整数，字段的增值操作后的值
//     */
//    public Long hincrBy(String key, String field, int number, int indexDb) {
//        long s = 0L;
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return null;
//        }
//        try {
//            jedis.select(indexDb);
//            s = jedis.hincrBy(key, field, number);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//            return s;
//        } finally {
//            returnResource(jedis);
//        }
//        return s;
//    }
//
//    /**
//     * 从所述一组保存在键中删除指定的元素。如果成员不存在，则命令返回0，如果没有设置在关键存储的值，则返回一个错误
//     *
//     * @param key
//     * @param value
//     * @param indexDb
//     */
//    public void delToSet(String key, String value, int indexDb) {
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return ;
//        }
//        try {
//            jedis.select(indexDb);
//            jedis.srem(key, value);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//        } finally {
//            returnResource(jedis);
//        }
//    }
//
//    /**
//     * 元素已经存在于集存储在键的键或不是
//     *
//     * @param key
//     * @param value
//     * @param indexDb
//     * @return 返回整型 1 如果该元素是集合的成员。 0 如果该元素不是集合的成员，或者如果键不存在
//     */
//    public boolean isExist(String key, String value, int indexDb) {
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return false;
//        }
//        boolean s = false;
//        try {
//            jedis.select(indexDb);
//            s = jedis.sismember(key, value);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//            return s;
//        } finally {
//            returnResource(jedis);
//        }
//        return s;
//    }
//
//    /**
//     * 检查键是否存在于Redis中
//     *
//     * @param key
//     * @param indexDb
//     * @return 如果键存在，返回 1 如果键不存在，返回 0
//     */
//    public boolean isKeyExist(String key, int indexDb) {
//        Jedis jedis = getJedis();
//        boolean s = false;
//        if(jedis==null){
//            return s;
//        }
//        try {
//            jedis.select(indexDb);
//            s = jedis.exists(key);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//            return s;
//        } finally {
//            returnResource(jedis);
//        }
//        return s;
//    }
//
//    /**
//     * 检查键是否存在于Redis中
//     *
//     * @param redisKeyEnum
//     * @param params
//     * @return 如果键存在，返回 1 如果键不存在，返回 0
//     */
//    public boolean isKeyExist(RedisKeyEnum redisKeyEnum,String... params) {
//        return isKeyExist(MessageFormat.format(redisKeyEnum.getKey(),params),redisKeyEnum.getDbIndex());
//    }
//
//    /**
//     * 所有的元素存在于集存储在指定的键
//     *
//     * @param key
//     * @param indexDb
//     * @return 数组返回该集合的所有元素
//     */
//    public Set<String> listAll(String key, int indexDb) {
//        Set<String> s = null;
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return null;
//        }
//        try {
//            jedis.select(indexDb);
//            s = jedis.smembers(key);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//            return s;
//        } finally {
//            returnResource(jedis);
//        }
//        return s;
//    }
//
//    /**
//     * 用于将存储在键上的数字按指定的值减少。如果键不存在，则在执行操作之前将其设置为0。
//     * 如果键包含错误类型的值或包含无法表示为整数的字符串，则会返回错误
//     *
//     * @param key
//     * @param number
//     * @param indexDb
//     * @return 返回一个整数，递减后键的值
//     */
//    public Long decrby(String key, long number, int indexDb) {
//        long s = 0L;
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return null;
//        }
//        try {
//            jedis.select(indexDb);
//            s = jedis.decrBy(key, number);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//            return s;
//        } finally {
//            returnResource(jedis);
//        }
//        return s;
//    }
//
//    /**
//     * 将键的整数值递增1。如果键不存在，则在执行操作之前将其设置为0。 如果键包含错误类型的值或包含无法表示为整数的字符串，则会返回错误。
//     *
//     * @param key
//     * @param indexDb
//     * @return 返回一个整数，增加后键的值
//     */
//    public Long incr(String key, int indexDb) {
//        long s = 0L;
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return null;
//        }
//        try {
//            jedis.select(indexDb);
//            s = jedis.incr(key);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//            return s;
//        } finally {
//            returnResource(jedis);
//        }
//        return s;
//    }
//
//    /**
//     * 将存储在键上的数字按指定的值增加。 如果键不存在，则在执行操作之前将其设置为0。如果键包含错误类型的值或包含无法表示为整数的字符串，则会返回错误。
//     *
//     * @param key
//     * @param increnumber
//     * @param indexDb
//     * @return 返回一个整数，增加后键的值
//     */
//    public Long incrby(String key, long increnumber, int indexDb) {
//        long s = 0L;
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return null;
//        }
//        try {
//            jedis.select(indexDb);
//            s = jedis.incrBy(key, increnumber);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//            return s;
//        } finally {
//            returnResource(jedis);
//        }
//        return s;
//    }
//
//    /**
//     * 将所有元素的参数保存在指定为第一个参数的键名的HyperLogLog数据结构 可用作IP统计,和pfcount一起使用
//     *
//     * @param key
//     * @param indexDb
//     * @param value
//     * @return 返回整数, 1 或 0.
//     */
//    public Long pfadd(String key, int indexDb, String... value) {
//        long s = 0L;
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return s;
//        }
//        try {
//            jedis.select(indexDb);
//            s = jedis.pfadd(key, value);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//            return s;
//        } finally {
//            returnResource(jedis);
//        }
//        return s;
//    }
//
//    /**
//     * 获得近似的基数由存储在指定变量的HyperLogLog数据结构进行计算。如果键不存在，则返回0
//     *
//     * @param key
//     * @param indexDb
//     * @return 返回整型，近似独特的元素数量。 当PFCOUNT命令使用多个键，则返回HyperLogLog联合近似基数。
//     */
//    public Long pfcount(String key, int indexDb) {
//        long s = 0L;
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return s;
//        }
//        try {
//            jedis.select(indexDb);
//            s = jedis.pfcount(key);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//            return s;
//        } finally {
//            returnResource(jedis);
//        }
//        return s;
//    }
//
//    /**
//     * 删除当前选择的数据块的键。此命令不会失败
//     *
//     * @param indexDb
//     * @return OK
//     */
//    public String flushDB(int indexDb) {
//        String s = "";
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return s;
//        }
//        try {
//            jedis.select(indexDb);
//            s = jedis.flushDB();
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//            return null;
//        } finally {
//            returnResource(jedis);
//        }
//        return s;
//    }
//
//    /**
//     * 用来得到所选数据库key数量
//     *
//     * @param indexDb db
//     * @return 整型, 数量
//     */
//    public Long size(int indexDb) {
//        long s = 0L;
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return null;
//        }
//        try {
//            jedis.select(indexDb);
//            s = jedis.dbSize();
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//        } finally {
//            returnResource(jedis);
//        }
//        return s;
//    }
//
//    /**
//     查找所有符合给定模式 pattern 的 key 。
//
//     KEYS * 匹配数据库中所有 key 。
//     KEYS h?llo 匹配 hello ， hallo 和 hxllo 等。
//     KEYS h*llo 匹配 hllo 和 heeeeello 等。
//     KEYS h[ae]llo 匹配 hello 和 hallo ，但不匹配 hillo 。
//     特殊符号用 \ 隔开
//
//     KEYS 的速度非常快，但在一个大的数据库中使用它仍然可能造成性能问题，如果你需要从一个数据集中查找特定的 key ，你最好还是用 Redis 的集合结构(set)来代替
//     * @param pattern
//     * @param indexDb
//     * @return 符合给定模式的 key 列表
//     */
//    public Set<String> keys(String pattern,int indexDb) {
//        Jedis jedis = getJedis();
//        if(jedis==null){
//            return null;
//        }
//        try {
//            jedis.select(indexDb);
//            return jedis.keys(pattern);
//        } catch (Exception e) {
//            returnBrokenResource(jedis);
//            e.printStackTrace();
//            return null;
//        } finally {
//            returnResource(jedis);
//        }
//    }
//
    /**
     * 释放资源
     * @param jedis redis java 客户端
     */
    private void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
            // jedisPool.returnResource(jedis);//jedis 2.7.0以后
            // jedis.close()替代了returnResource
        }
    }

    /**
     * 释放资源
     * @param jedis redis java 客户端
     */
    private void returnBrokenResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
            // jedisPool.returnBrokenResource(jedis);//jedis 2.7.0以后
            // jedis.close()替代了returnBrokenResource
        }
    }







}
