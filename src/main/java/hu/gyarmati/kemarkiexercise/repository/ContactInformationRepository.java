package hu.gyarmati.kemarkiexercise.repository;

import hu.gyarmati.kemarkiexercise.domain.ContactInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactInformationRepository extends JpaRepository<ContactInformation, Long> {
}
