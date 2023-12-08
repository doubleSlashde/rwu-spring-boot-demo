package de.rwu.swen.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration properties class via which the paging can be configured
 * that is used in the {@link de.rwu.swen.demo.controller.StudentController}.
 *
 * In application.properties, the page size can be configured via the config
 * property paging.page-size.
 */
@Configuration
@ConfigurationProperties(prefix = "paging")
public class PagingProperties {

    // default page size is 10
    private int pageSize = 10;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
