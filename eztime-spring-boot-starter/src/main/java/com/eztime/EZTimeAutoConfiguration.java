package com.eztime;

import com.eztime.service.EZDateService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "eztime", name = "enabled", havingValue = "true", matchIfMissing = true)
public class EZTimeAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public EZDateService ezDateService() {
        return new EZDateService();
    }
}
