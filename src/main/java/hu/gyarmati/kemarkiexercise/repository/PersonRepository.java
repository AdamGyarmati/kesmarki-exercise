package hu.gyarmati.kemarkiexercise.repository;

import hu.gyarmati.kemarkiexercise.domain.AddressType;
import hu.gyarmati.kemarkiexercise.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("select p from Person p where p.id=:personId " +
            "and size(p.addressList) < 2" +
            "and 0 = (select count(a) from Address a where a.addressType=:addressType and a.person.id=:personId)")
    Person checkPersonByAddressTypeAndNumberOfAddressType(AddressType addressType, Long personId);
}
