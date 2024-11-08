package com.mainpackage.backend.controller;

import com.mainpackage.backend.model.Catalog;
import com.mainpackage.backend.service.CatalogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class CatalogController {

    private final CatalogService catalogService = new CatalogService();

    @GetMapping("/catalog/{catalogName}")
    public Catalog getCatalog(@PathVariable String catalogName) throws IOException {
        return catalogService.getCatalog(catalogName);
    }
}
