package hu.gyarmati.kemarkiexercise.repository;

import hu.gyarmati.kemarkiexercise.domain.AddressType;
import hu.gyarmati.kemarkiexercise.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("SELECT CASE WHEN (COUNT(p) > 0) THEN false ELSE true END " +
            "FROM Person p " +
            "WHERE p.id = :personId " +
            "AND SIZE(p.addressList) < 2 " +
            "AND 0 = (SELECT COUNT(a) FROM Address a WHERE a.addressType = :addressType AND a.person.id = :personId)")
    boolean checkPersonByAddressTypeAndNumberOfAddressType(AddressType addressType, Long personId);
}
