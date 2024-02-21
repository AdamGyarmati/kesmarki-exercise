package hu.gyarmati.kemarkiexercise.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "contact_information")
public class ContactInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contact_information")
    private String contactInfo;

    @Enumerated(EnumType.STRING)
    private ContactInformationType contactInformationType;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
}
