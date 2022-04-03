package repo;

import org.springframework.data.jpa.repository.JpaRepository;

import models.Word;

public interface WordRepo extends JpaRepository<Word, Long>{

}
