package com.example.demo;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

// Unit test
@SpringBootTest
class DemoApplicationTests {
	@Autowired
	DataSource dataSource;

	@Test
	void contextLoads() throws SQLException {
		System.out.println(dataSource.getClass());

		Connection connection =  dataSource.getConnection();
		System.out.println(connection);

		DruidDataSource druidDataSource = (DruidDataSource) dataSource;
		System.out.println("druidDataSource 数据源最大连接数：" + druidDataSource.getMaxActive());
		System.out.println("druidDataSource 数据源初始化连接数：" + druidDataSource.getInitialSize());

		connection.close();
	}

}
