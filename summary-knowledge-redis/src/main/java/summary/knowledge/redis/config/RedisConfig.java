package summary.knowledge.redis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RedisConfig {

	private String hostName = "127.0.0.1";
	private int port = 6379;

	public LettuceConnectionFactory redisConnectionFactory() {
		return new LettuceConnectionFactory(new RedisStandaloneConfiguration(hostName, port));
	}

	public JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(hostName, port);
		return new JedisConnectionFactory(config);
	}

	// redis集群配置方式
	public RedisConnectionFactory jedisSentineConnectionFactory() {
		RedisSentinelConfiguration sendinelConfig = new RedisSentinelConfiguration().master("master")
				.sentinel(hostName, port).sentinel(hostName, port);
		return new JedisConnectionFactory(sendinelConfig);
	}
}
