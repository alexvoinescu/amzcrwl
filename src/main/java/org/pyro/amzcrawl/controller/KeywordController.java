package org.pyro.amzcrawl.controller;

import org.pyro.amzcrawl.model.Keyword;
import org.pyro.amzcrawl.services.KeywordExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.pyro.amzcrawl.repository.KeywordRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class KeywordController {

    @Autowired
    KeywordRepository keywordRepository;

    @PostMapping("/extract/keywords")
    public void extractKeywords(@Valid @RequestBody Map<String, String> attr) {
        KeywordExtractor kw = new KeywordExtractor(attr.get("text"));
        kw.getKeywords();
    }

    @GetMapping("/keywords")
    public List<Keyword> getAllKeywords() {
        return keywordRepository.findAll();
    }

    @PostMapping("/keywords")
    public Keyword createKeyword(@Valid @RequestBody Keyword keyword) {
        return keywordRepository.save(keyword);
    }

    @GetMapping("keywords/{keywordId}")
    public ResponseEntity<Keyword> getKeywordById(@PathVariable(value = "keywordId") Long keywordId) {
        Keyword keyword = keywordRepository.findOne(keywordId);
        if(keyword == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(keyword);
    }

    @PutMapping("keywords/{keywordId}")
    public ResponseEntity<Keyword> updateKeyword(@PathVariable(value = "keywordId") Long keywordId, @Valid @RequestBody Keyword keywordDetails) {
        Keyword keyword = keywordRepository.findOne(keywordId);
        if(keyword == null) {
            return ResponseEntity.notFound().build();
        }

        keyword.setWords(keyword.getWords());

        Keyword updatedKeywords = keywordRepository.save(keyword);
        return ResponseEntity.ok(updatedKeywords);
    }

    @DeleteMapping("keywords/{keywordId}")
    public ResponseEntity<Keyword> deleteKeyword(@PathVariable(value = "keywordId") Long keywordId) {
        Keyword keyword = keywordRepository.findOne(keywordId);
        if(keyword == null) {
            return ResponseEntity.notFound().build();
        }

        keywordRepository.delete(keywordId);
        return ResponseEntity.ok().build();
    }

}
