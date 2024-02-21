package hu.gyarmati.kemarkiexercise.repository;

import hu.gyarmati.kemarkiexercise.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
