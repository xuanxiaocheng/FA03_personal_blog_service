package app.example.blogBE.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.*;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.time.Duration;

@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    //在缓存对象集合中，缓存是以key-value形式保存的
    //如果没有指定缓存的key，则Spring Boot 会使用SimpleKeyGenerator生成key
    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            //定义缓存key的生成策略
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }
    @SuppressWarnings("rawtypes")
    @Bean
    //缓存管理器 2.X版本
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisSerializer<String> strSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer jacksonSeial = new Jackson2JsonRedisSerializer(Object.class);

        // 解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jacksonSeial.setObjectMapper(om);

        // 定制缓存数据序列化方式及时效
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofDays(1))
                .serializeKeysWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(strSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(jacksonSeial))
                .disableCachingNullValues();
        RedisCacheManager cacheManager = RedisCacheManager
                .builder(connectionFactory).cacheDefaults(config).build();
        return cacheManager;
    }
    //缓存管理器 1.X版本
/*    public CacheManager cacheManager(@SuppressWarnings("rawtypes")RedisTemplate redisTemplate){
        return new RedisCacheManager(redisTemplate);
    }*/

    @Bean
    //注册成Bean被Spring管理，如果没有这个Bean，则Redis可视化工具中的中文内容都会以二进制存储，不易检查
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        // 创建JSON格式序列化对象，对缓存数据的key和value进行转换
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        // 解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        //设置redisTemplate模板API的序列化方式为json
        template.setDefaultSerializer(jackson2JsonRedisSerializer);
        return template;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory){
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(factory);
        return stringRedisTemplate;
    }
    private RedisSerializer<?> keySerializer() {
        return new StringRedisSerializer();
    }

    // 值使用jackson进行序列化
    private RedisSerializer<?> valueSerializer() {
        return new GenericJackson2JsonRedisSerializer();
        // return new JdkSerializationRedisSerializer();
    }


    /**
     自定义RedisCacheManager，用于在使用@Cacheable时设置ttl
     */
    @Primary
    @Bean
    public RedisCacheManager selfCacheManager(RedisTemplate<String, Object> redisTemplate) {
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisTemplate.getConnectionFactory());
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getValueSerializer()));
        return new TtlRedisCacheManager(redisCacheWriter, redisCacheConfiguration);
    }

    public class TtlRedisCacheManager extends RedisCacheManager {
        public TtlRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
            super(cacheWriter, defaultCacheConfiguration);
        }

        @Override
        protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
            String[] cells = StringUtils.delimitedListToStringArray(name, "=");
            name = cells[0];
            if (cells.length > 1) {
                long ttl = Long.parseLong(cells[1]);
                // 根据传参设置缓存失效时间，默认单位是秒
                cacheConfig = cacheConfig.entryTtl(Duration.ofSeconds(ttl));
            }else {
                cacheConfig = cacheConfig.entryTtl(Duration.ofSeconds(600));
            }
            return super.createRedisCache(name, cacheConfig);
        }
    }


}