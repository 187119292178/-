package com.aishangrun.aishangrun.config.druid;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.*;

/**
 * Describe:数据库连接池Druid配置
 * -------------------
 * User: yangyongsheng
 * Date: 2019/06/22 12:07:28
 * Email: 1095737364@qq.com
 */
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix ="spring.datasource")
    @Bean(destroyMethod = "close", initMethod = "init")
    public DataSource druid() {
        return new DruidDataSource();
    }

    // 配置Druid的监控
    // 1、配置一个管理后台的Servlet
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

        Map<String, String> initParams = new HashMap<>();

        // 监控页面登录用户名
        initParams.put("loginUsername", "admin");
        // 监控页面登录用户密码
        initParams.put("loginPassword", "123456");
        // ip白名单（没有配置或者为空，则允许所有访问）
        initParams.put("allow", "");
        // ip黑名单（如果某个ip同时存在，deny优先于allow）
        initParams.put("deny", "");

        bean.setInitParameters(initParams);
        return bean;
    }

    // 2、配置一个web监控的filter
    @Bean
        public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String, String> initParams = new HashMap<>();

        // 不拦截的静态资源
        initParams.put("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        bean.setInitParameters(initParams);

        // 拦截所有的请求
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
    //使用连接池dataSource
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        List<Filter> filterList = new ArrayList<>();
        filterList.add(wallFilter());
        druidDataSource.setProxyFilters(filterList);
        return druidDataSource;

    }

    @Bean
    public WallFilter wallFilter() {
        WallFilter wallFilter = new WallFilter();
        wallFilter.setConfig(wallConfig());
        return wallFilter;
    }

    @Bean
    public WallConfig wallConfig() {
        WallConfig config = new WallConfig();
        config.setMultiStatementAllow(true);//允许一次执行多条语句
        config.setNoneBaseStatementAllow(true);//允许非基本语句的其他语句
        return config;
    }

}
