package com.acme.catalog.web;

import com.acme.catalog.model.Product;
import com.acme.catalog.service.ProductSearchService;
import java.io.IOException;
import java.util.List;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductSearchService searchService;

    public ProductController(ProductSearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public List<Product> search(
            @RequestParam("q") String query,
            @RequestParam(value = "rows", defaultValue = "20") int rows)
            throws IOException, SolrServerException {
        return searchService.search(query, Math.min(rows, 100));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void index(@RequestBody Product product) throws IOException, SolrServerException {
        searchService.index(product);
    }
}
