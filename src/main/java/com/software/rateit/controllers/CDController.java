package com.software.rateit.controllers;

import com.software.rateit.Entity.CD;
import com.software.rateit.repositories.CDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CDController {

    @Autowired
    private CDRepository repository;

    @GetMapping("/cds")
    Iterable<CD> getAllCDs() {
        return repository.findAll();
    }

    @GetMapping("/cds/{id}")
    CD getCDById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CouldNotFindException(id));
    }
    @GetMapping("/CDByName")
    public CD findCdByName(
            @RequestParam("name") String name) {
        if(name != null)
            return repository.findByName(name);
        else
            throw new CouldNotFindException(name);
    }
    @GetMapping("/CDByReleased")
    public CD findCdByReleased(
            @RequestParam("released")
            Date released) {
        if(released != null)
            return repository.findByReleased(released);
        else
            throw new CouldNotFindException(released);
    }

    @PostMapping("/cds")
    CD newCD(@RequestBody CD newCD) {
        return repository.save(newCD);
    }

    @PutMapping("/cds/{id}")
    CD replaceCD(@RequestBody CD newCD, @PathVariable Long id) {
        return repository.findById(id)
                .map(cd -> {
                    cd.setArtist(newCD.getArtist());
                    cd.setGenres(newCD.getGenres());
                    cd.setName(newCD.getName());
                    cd.setReleased(newCD.getReleased());
                    cd.setRating(newCD.getRating());
                    cd.setCdtracks(newCD.getCdtracks());
                    return repository.save(cd);
                })
                .orElseGet(() -> {
                    newCD.setId(id);
                    return repository.save(newCD);
                });
    }

    @DeleteMapping("/cds/{id}")
    void deleteCD(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
