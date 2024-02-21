package hu.gyarmati.kemarkiexercise.repository;

import hu.gyarmati.kemarkiexercise.domain.Address;
import hu.gyarmati.kemarkiexercise.domain.AddressType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("select case when COUNT(a) > 0 then true else false end from Address a" +
            " where a.addressType = :addressType and a.person.id = :personId")
    boolean existsByAddressTypeAndPersonId(AddressType addressType, Long personId);
}
