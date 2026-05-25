package com.springboot.assistant.repository;

import com.springboot.assistant.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VectorRepository extends JpaRepository<Document, Long> {

    @Query(value = "SELECT * FROM documents WHERE content % :query ORDER BY content <=> :query LIMIT 5", nativeQuery = true)
    List<Document> searchBySimilarity(@Param("query") String query);
}
