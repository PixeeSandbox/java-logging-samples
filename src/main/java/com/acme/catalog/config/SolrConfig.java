package com.acme.catalog.config;

import java.nio.file.Path;
import java.util.Properties;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.core.CoreContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SolrConfig {

    @Bean(destroyMethod = "shutdown")
    public CoreContainer coreContainer(@Value("${catalog.solr-home}") String solrHome) {
        CoreContainer container = new CoreContainer(Path.of(solrHome).toAbsolutePath(), new Properties());
        container.load();
        return container;
    }

    @Bean(destroyMethod = "close")
    public SolrClient solrClient(CoreContainer coreContainer) {
        return new EmbeddedSolrServer(coreContainer, "products");
    }
}
