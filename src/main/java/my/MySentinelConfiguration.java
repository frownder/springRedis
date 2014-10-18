package my;

import java.util.Set;

import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.stereotype.Component;


public class MySentinelConfiguration extends RedisSentinelConfiguration {
	
	MySentinelConfiguration(){
		super();
	}
	
	public Set<RedisNode> getMySentinels(){
		
		Set<RedisNode> sets=super.getSentinels();
		System.out.println( "IN getMySentinels size()=" + sets.size()  );
		
		return sets;
	}
	
	public void setMySentinels( Set<RedisNode> sentinels){
		
		System.out.println( sentinels.size() + "IN setMySentinels size()=");
		super.setSentinels(sentinels);
	}

}
