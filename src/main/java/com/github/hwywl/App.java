package com.github.hwywl;

import com.github.hwywl.config.LogHubProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置
 *
 * @author YI
 * @date 2020-6-2
 */
@Configuration
@ComponentScan
@EnableConfigurationProperties(LogHubProperties.class)
public class App {

}
