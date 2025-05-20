package dop.arahnik.postgres_service.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import dop.arahnik.postgres_service.models.Verse;
import dop.arahnik.postgres_service.service.VerseService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/verses")
@RequiredArgsConstructor
public class VerseController {

    private final VerseService verseService;

    @GetMapping("/{id}")
    public ResponseEntity<Verse> getVerseById(@PathVariable Long id) {
        return ResponseEntity.ok(verseService.getVerseById(id));
    }

    @GetMapping
    public ResponseEntity<List<Verse>> getAllVerses() {
        return ResponseEntity.ok(verseService.getAllVerses());
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<Verse>> getVersesByAuthor(@PathVariable String author) {
        return ResponseEntity.ok(verseService.getVersesByAuthor(author));
    }

    @PostMapping
    public ResponseEntity<Verse> createVerse(@RequestBody Verse verse) {
        return ResponseEntity.ok(verseService.createVerse(verse));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Verse> updateVerse(@PathVariable Long id, @RequestBody Verse verse) {
        verse.setId(id);
        return ResponseEntity.ok(verseService.updateVerse(verse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVerse(@PathVariable Long id) {
        verseService.deleteVerse(id);
        return ResponseEntity.noContent().build();
    }
}
