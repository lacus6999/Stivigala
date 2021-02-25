package com.stivigala.wolt;

import com.stivigala.wolt.dbo.Storage;
import com.stivigala.wolt.dbo.StorageRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WoltRestController
{
    private final StorageRepo storageRepo;

    public WoltRestController(StorageRepo storageRepo) {
        this.storageRepo = storageRepo;
    }

    @GetMapping("/allStorage")
    public String getAllStorage() {
        return storageRepo.findAll().iterator().next().getName();
    }
}
