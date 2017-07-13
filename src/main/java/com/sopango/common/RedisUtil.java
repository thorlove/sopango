package com.sopango.common;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.exceptions.JedisException;

public class RedisUtil {

    private static Logger log = Logger.getLogger(RedisUtil.class);

    private static void release(Jedis jd) {
        RedisPool.close(jd);
    }

    private static Jedis getJedis() {
        Jedis jedis = RedisPool.getJedis();
        return jedis;
    }

    /**
     * 添加字符串
     * 
     * @param key
     * @param value
     * @param cacheSeconds
     * @param methodName
     * @return
     */
    public static String addStringToJedis(String key, String value,int cacheSeconds, String methodName) {
        Jedis jedis = null;
        String lastVal = null;
        try {
            jedis = getJedis();
            lastVal = jedis.getSet(key, value);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
            log.error("failed:" + methodName + "key=" + key + "value=" + value,e);
        } finally {
            release(jedis);
        }
        return lastVal;
    }
    
    /**
     * 存储一个对象,不建议使用对象的存储(序列化的效率不是很高)
     * @param key
     * @param value
     * @param cacheSeconds
     * @param methodName
     */
    public static void setObject(String key,Object value,int cacheSeconds,String methodName){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.set(SerializeUtil.serialize(key), SerializeUtil.serialize(value));
            if(cacheSeconds!=0){
                jedis.expire(SerializeUtil.serialize(key), cacheSeconds);
            }
        } catch (Exception e) {
            log.error("failed:" + methodName + "key=" + key + "value=" + value,
                    e);
        } finally {
            release(jedis);
        }
    }
    
    /**
     * 获取Object对象
     * @param key
     * @param methodName
     * @return
     */
    public static Object getObject(String key,String methodName){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            byte[] data = jedis.get(SerializeUtil.serialize(key));
            if (data != null) return SerializeUtil.deserialize(data);           
        } catch (Exception e) {
            log.error("failed:" + methodName + "key=" + key ,e);
        } finally {
            release(jedis);
        }
        return null;
    }
    
    /**
     * 获取Object对象
     * @param key
     * @param methodName
     * @return
     */
    public static void delObject(String key,String methodName){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.del(SerializeUtil.serialize(key));
        } catch (Exception e) {
            log.error("failed:" + methodName + "key=" + key ,e);
        } finally {
            release(jedis);
        }
    }   
    
    /**
     * 批量存储字符串
     * @param batchData
     * @param cacheSeconds
     * @param methodName
     */
    public static void addStringToJedis(Map<String, String> batchData,
            int cacheSeconds, String methodName) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            Pipeline pipeline = jedis.pipelined();
            for (Map.Entry<String, String> element : batchData.entrySet()) {
                if (cacheSeconds != 0) {
                    pipeline.setex(element.getKey(), cacheSeconds,
                            element.getValue());
                } else {
                    pipeline.set(element.getKey(), element.getValue());
                }
            }
            pipeline.sync();
        } catch (Exception e) {
            log.error("failed:" + methodName + "size=" + batchData.size(), e);
        } finally {
            release(jedis);
        }
    }
    
    /**
     * 存储list
     * 如果 key 不存在，一个空列表会被创建并执行 RPUSH 操作
     * 当 key 存在但不是列表类型时，返回一个错误
     * @param key
     * @param list
     * @param cacheSeconds
     * @param methodName
     */
    public static void addListToJedis(String key, List<String> list,
            int cacheSeconds, String methodName) {
        if (list != null && list.size() > 0) {
            Jedis jedis = null;
            
            try {
                jedis = getJedis();
                
                if (jedis.exists(key)) {
                    jedis.del(key);
                }
                for (String aList : list) {
                    jedis.rpush(key, aList);
                }
                if (cacheSeconds != 0) {
                    jedis.expire(key, cacheSeconds);
                }
            } catch (JedisException e) {
                
                log.error("failed:" + methodName + "key=" + key + "size="
                        + list.size(), e);
            } catch (Exception e) {
                
                log.error("failed:" + methodName + "key=" + key + "size="
                        + list.size(), e);
            } finally {
                release(jedis);
            }
        }
    }

    /**
     * 创建一个set，并存入值
     * @param key
     * @param value
     * @param methodName
     */
    public static void addToSetJedis(String key, String[] value, String methodName) {
        Jedis jedis = null;
        
        try {
            jedis = getJedis();
            
            jedis.sadd(key, value);
        } catch (Exception e) {
            
            log.error("failed:" + methodName + "key=" + key + "value=" + value,
                    e);
        } finally {
            release(jedis);
        }
    }

    public static void deleteSetJedis(String key, String value, String methodName) {
        Jedis jedis = null;
        
        try {
            jedis = getJedis();
            
            jedis.srem(key, value);
        } catch (Exception e) {
            
            log.error("failed:" + methodName + "key=" + key + "value=" + value,
                    e);
        } finally {
            release(jedis);
        }
    }

    /**
     * 将值存入list中，从左到右的顺序依次插入到表尾
     * 如果 key 不存在，一个空列表会被创建并执行 RPUSH 操作
     * @param key
     * @param data
     * @param cacheSeconds
     * @param methodName
     */
    public static void pushDataToListJedis(String key, String data,
            int cacheSeconds, String methodName) {
        Jedis jedis = null;
        
        try {
            jedis = getJedis();
            
            jedis.rpush(key, data);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
            
            log.error("failed:" + methodName + "key=" + key + "data=" + data, e);
        } finally {
            release(jedis);
        }
    }

    /**
     * 将值批量存入list中，从左到右的顺序依次插入到表尾
     * 如果 key 不存在，一个空列表会被创建并执行 RPUSH 操作
     * @param key
     * @param batchData
     * @param cacheSeconds
     * @param methodName
     */
    public static void pushDataToListJedis(String key, List<String> batchData,
            int cacheSeconds, String methodName) {
        Jedis jedis = null;
        
        try {
            jedis = getJedis();
            
            jedis.del(key);
            jedis.lpush(key, batchData.toArray(new String[batchData.size()]));
            if (cacheSeconds != 0)
                jedis.expire(key, cacheSeconds);
        } catch (Exception e) {
            
            log.error("failed:" + methodName + "key=" + key + "size="
                    + batchData != null ? batchData.size() : 0, e);
        } finally {
            release(jedis);
        }
    }

    /**
     * 删除list中的元素
     * 
     * @param key list
     * @param values 要删除的元素
     * @param methodName
     */
    public static void deleteDataFromListJedis(String key, List<String> values,
            String methodName) {
        Jedis jedis = null;
        
        try {
            jedis = getJedis();
            
            Pipeline pipeline = jedis.pipelined();
            if (values != null && !values.isEmpty()) {
                for (String val : values) {
                    pipeline.lrem(key, 0, val);
                }
            }
            pipeline.sync();
        } catch (Exception e) {
            log.error("failed:" + methodName + "key=" + key + "size=" + values != null ? values.size() : 0, e);
        } finally {
            release(jedis);
        }
    }
    
    /**
     * 存储map
     * 如果key存在 会覆盖map中已存在的域
     * 如果 key 不存在，一个空map被创建并执行 HMSET 操作
     * @param key
     * @param map
     * @param cacheSeconds
     * @param methodName
     */
    public static void addHashMapToJedis(String key, Map<String, String> map,
            int cacheSeconds, String methodName) {
        Jedis jedis = null;
        if (map != null && map.size() > 0) {
            try {
                jedis = getJedis();
                jedis.hmset(key, map);
                if (cacheSeconds != 0)
                    jedis.expire(key, cacheSeconds);
            } catch (Exception e) {
                log.error("failed:" + methodName + "key=" + key + "size="+ map.size(), e);
            } finally {
                release(jedis);
            }
        }
    }

    /**
     * 将map中的域 field 的值设为 value,
     * key 不存在，一个新的哈希表被创建并进行 HSET 操作
     * 如果域 field 已经存在于哈希表中，旧值将被覆盖
     * @param key
     * @param field
     * @param value
     * @param cacheSeconds
     * @param methodName
     */
    public static void addHashMapToJedis(String key, String field, String value,
            int cacheSeconds, String methodName) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (jedis != null) {
                jedis.hset(key, field, value);
                if(cacheSeconds != 0)
                    jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
            log.error("failed:" + methodName + "key=" + key + "field=" + field+ "value=" + value, e);
        } finally {
            release(jedis);
        }
    }
    
    /**
     * 修改map中存储的值
     * @param key
     * @param incrementField
     * @param incrementValue
     * @param dateField
     * @param dateValue
     * @param methodName
     */
    public static void updateHashMapToJedis(String key, String dateField, String dateValue,
            String methodName) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.hset(key, dateField, dateValue);
        } catch (Exception e) {
            log.error("failed:" + methodName + "key=" + key + "field="+ dateField + "value=" + dateValue, e);
        } finally {
            release(jedis);
        }
    }

    public static void updateHashMapToJedis(String key, String incrementField,
            long incrementValue, String dateField, String dateValue,
            String methodName) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.hincrBy(key, incrementField, incrementValue);
            jedis.hset(key, dateField, dateValue);
        } catch (Exception e) {
            log.error("failed:" + methodName + "key=" + key + "field=" + incrementField + "value=" + incrementValue, e);
        } finally {
            release(jedis);
        }
    }
    
    /**
     * 删除名称为 key 的 hash 中键为 field 的域
     * @param key
     * @param fileds
     */
    public static void deleteDateFromHashMap(String key,String[] fileds,String methodName) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            Pipeline pipeline = jedis.pipelined();
            if (fileds != null && fileds.length>0) {
                for (String val : fileds) {
                    pipeline.hdel(key, val);
                }
            }
            pipeline.sync();
        } catch (Exception e) {
            log.error("failed:" + methodName + "key=" + key + "size=" + fileds != null ? fileds.length : 0, e);
        } finally {
            release(jedis);
        }   
    }
    
    public static String getStringFromJedis(String key, String methodName) {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (jedis.exists(key)) {
                value = jedis.get(key);
                value = StringUtils.isNotBlank(value)&& !"nil".equalsIgnoreCase(value) ? value : null;
            }
        } catch (Exception e) {
            log.error("failed:" + methodName + "key=" + key + "value=" + value,e);
        } finally {
            release(jedis);
        }
        return value;
    }

    public static List<String> getStringFromJedis(String[] keys, String methodName) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.mget(keys);
        } catch (Exception e) {
            log.error("failed:" + methodName + "keys=" + Arrays.toString(keys), e);
        } finally {
            release(jedis);
        }
        return null;
    }

    public static List<String> getListFromJedis(String key, String methodName) {
        List<String> list = null;
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (jedis.exists(key)) {
                list = jedis.lrange(key, 0, -1);
            }
        } catch (JedisException e) {
            log.error("failed:" + methodName + "key=" + key + "size=" + list != null ? list.size() : 0, e);
        } finally {
            release(jedis);
        }
        return list;
    }

    public static Set<String> getSetFromJedis(String key, String methodName) {
        Set<String> list = null;
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (jedis.exists(key)) {
                list = jedis.smembers(key);
            }
        } catch (Exception e) {
            log.error("failed:" + methodName + "key=" + key + "size=" + list != null ? list.size() : 0, e);
        } finally {
            release(jedis);
        }
        return list;
    }

    protected static Map<String, String> getHashMapFromJedis(String key,
            String methodName) {
        Map<String, String> hashMap = null;
        Jedis jedis = null;
        try {
            jedis = getJedis();
            hashMap = jedis.hgetAll(key);
        } catch (Exception e) {
            log.error(
                    "failed:" + methodName + "key=" + key + "size=" + hashMap != null ? hashMap.size() : 0, e);
        } finally {
            release(jedis);
        }
        return hashMap;
    }

    protected static String getHashMapValueFromJedis(String key, String field,
            String methodName) {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = getJedis();
            if (jedis != null) {
                if (jedis.exists(key)) {
                    value = jedis.hget(key, field);
                }
            }
        } catch (Exception e) {
            log.error("failed:" + methodName + "key=" + key + "field=" + field + "value=" + value, e);
        } finally {
            release(jedis);
        }
        return value;
    }

    public static Long getIdentifyId(String identifyName, String methodName) {
        Jedis jedis = null;
        Long identify = null;
        try {
            jedis = getJedis();
            if (jedis != null) {
                
                identify = jedis.incr(identifyName);
                if (identify == 0) {
                    return jedis.incr(identifyName);
                } else {
                    return identify;
                }
            }
        } catch (Exception e) {
            log.error("failed:" + methodName + "identifyName=" + identifyName + "identify=" + identify, e);
        } finally {
            release(jedis);
        }
        return null;
    }

    /**
     * 删除某db的某个key值
     * 
     * @param key
     * @return
     */
    public static Long delKeyFromJedis(String key) {
        
        Jedis jedis = null;
        long result = 0;
        try {
            jedis = getJedis();
            return jedis.del(key);
        } catch (Exception e) {
            log.error("failed:delKeyFromJedis", e);
        } finally {
            release(jedis);
        }
        return result;
    }

    /**
     * 根据dbIndex flushDB每个shard
     * 
     * @param dbIndex
     */
    public static void flushDBFromJedis(int dbIndex) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            jedis.flushDB();
        } catch (Exception e) {
            log.error("failed:flushDBFromJedis", e);
        } finally {
            release(jedis);
        }
    }

    public static boolean existKey(String key, String methodName) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.exists(key);
        } catch (Exception e) {
            log.error("failed:" + "methodName=" + methodName, e);
        } finally {
            release(jedis);
        }
        return false;
    }
}
