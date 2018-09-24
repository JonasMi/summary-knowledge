package summary.knowledge.mybatis;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @ Author ：JieMi
 * @ Date   ：Created in 2018/9/14 23:07
 * @ version: 1.0.0
 */
@SpringBootApplication
@EnableAutoConfiguration
@ImportResource({"classpath:mybatis.xml"})
public class ApplicationMybatis {
    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(ApplicationMybatis.class, args);
        HikariDataSource dataSource = (HikariDataSource)((ConfigurableApplicationContext) applicationContext).getBeanFactory().getBean(DataSource.class);
        String[] beanNames = ((ConfigurableApplicationContext) applicationContext).getBeanFactory().getBeanNamesForType(DataSource.class);
        System.out.println(dataSource);
        System.out.println(dataSource.getMaximumPoolSize());
        /*try {
            dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }
}
