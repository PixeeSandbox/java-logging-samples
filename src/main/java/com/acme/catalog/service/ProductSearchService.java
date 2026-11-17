package com.acme.catalog.service;

import com.acme.catalog.model.Product;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductSearchService {

    private static final Logger log = LoggerFactory.getLogger(ProductSearchService.class);

    private final SolrClient solr;

    public ProductSearchService(SolrClient solr) {
        this.solr = solr;
    }

    public void index(Product product) throws IOException, SolrServerException {
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", product.getId());
        doc.addField("name", product.getName());
        solr.add(doc);
        solr.commit();
    }

    public List<Product> search(String query, int rows) throws IOException, SolrServerException {
        SolrQuery solrQuery = new SolrQuery(query).setRows(rows);
        List<Product> results = solr.query(solrQuery).getResults().stream()
                .map(ProductSearchService::toProduct)
                .collect(Collectors.toList());
        log.info("Search '{}' returned {} products", query, results.size());
        return results;
    }

    private static Product toProduct(SolrDocument doc) {
        return new Product((String) doc.getFirstValue("id"), (String) doc.getFirstValue("name"));
    }
}
