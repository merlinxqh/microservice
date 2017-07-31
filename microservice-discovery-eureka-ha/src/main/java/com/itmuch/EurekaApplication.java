package com.itmuch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * eureka通过运行多个实例并相互注册的方式 实现高可用不熟, eureka server实例会彼此增量的同步信息,从而确保所有节点数据一致.
 * 事实上, 节点之间相互注册时Eureka server的默认行为
 */
@SpringBootApplication
@EnableEurekaServer //声明这是一个eureka server
public class EurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaApplication.class, args);
	}
}
