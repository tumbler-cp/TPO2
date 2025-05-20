package dop.arahnik.postgres_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dop.arahnik.postgres_service.models.Verse;
import java.util.List;


@Repository
public interface VerseRepository extends JpaRepository<Verse, Long> {
    List<Verse> findAllByAuthor(String author);
}
