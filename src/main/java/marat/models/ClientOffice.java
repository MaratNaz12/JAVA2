package marat.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clientoffice")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class ClientOffice implements CommonEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "office")
    @ToString.Exclude
    @NonNull
    private Office office;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client")
    @ToString.Exclude
    @NonNull
    private Client client;


}
