package dop.arahnik.postgres_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dop.arahnik.postgres_service.models.Verse;
import dop.arahnik.postgres_service.repository.VerseRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VerseService {
    private final VerseRepository verseRepository;

    public Verse getVerseById(Long id) {
        return verseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Verse not found"));
    }

    public List<Verse> getAllVerses() {
        return verseRepository.findAll();
    }

    public List<Verse> getVersesByAuthor(String author) {
        return verseRepository.findAllByAuthor(author);
    }

    public Verse createVerse(Verse verse) {
        return verseRepository.save(verse);
    }

    public Verse updateVerse(Verse verse) {
        if (!verseRepository.existsById(verse.getId())) {
            throw new RuntimeException("Verse not found");
        }
        return verseRepository.save(verse);
    }

    public void deleteVerse(Long id) {
        if (!verseRepository.existsById(id)) {
            throw new RuntimeException("Verse not found");
        }
        verseRepository.deleteById(id);
    }



}
