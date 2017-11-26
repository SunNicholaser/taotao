package com.taotao.jedis;

import com.taotao.rest.component.JedisClient;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import javax.sound.midi.Soundbank;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ASUS on 2017/11/13.
 */
public class JedisTest {

    @Test
    public void testJedisSingle() throws Exception {
        //创建一个Jedis对象
        Jedis jedis = new Jedis("192.168.33.129", 6379);
        jedis.set("test", "hello jedis");
        String string = jedis.get("test");
        System.out.println(string);
        jedis.close();
    }

    @Test
    public void testJedisPool() throws Exception{
        //创建一个连接池对象
        //系统中应该是单利的
        JedisPool jedisPool = new JedisPool("192.168.33.129",6379);
        Jedis jedis = jedisPool.getResource();
        String res = jedis.get("test");
        System.out.println(res);
        //  jedis必须关闭
        jedis.close();
        //系统关闭时，关闭连接池
        jedisPool.close();
    }
    //连接redis集群
        @Test
    public void testJedisCluster() throws Exception{
        //创建一个jediscluster
            //在nodes中指定节点的地址
            Set<HostAndPort> nodes = new HashSet<>();
            nodes.add(new HostAndPort("192.168.33.129",7001));
            nodes.add(new HostAndPort("192.168.33.129",7002));
            nodes.add(new HostAndPort("192.168.33.129",7003));
            nodes.add(new HostAndPort("192.168.33.129",7004));
            nodes.add(new HostAndPort("192.168.33.129",7005));
            nodes.add(new HostAndPort("192.168.33.129",7006));
            //在nodes中指定每个节点的地址
            JedisCluster jedisCluster = new JedisCluster(nodes);
            jedisCluster.set("name","zhangsan");
            jedisCluster.set("value","100");
            String name = jedisCluster.get("name");
            String value = jedisCluster.get("value");
            System.out.println(name);
            System.out.println(value);
            jedisCluster.close();
        }

    @Test
    public void testJedisClientSpring() throws Exception {
        //创建一个spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        //从容器中获得JedisClient对象
        JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
        //jedisClient操作redis
        jedisClient.set("cliet1", "1000");
        String string = jedisClient.get("cliet1");
        System.out.println(string);
    }

}
