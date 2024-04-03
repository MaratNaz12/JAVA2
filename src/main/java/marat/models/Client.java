package marat.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "client")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class Client implements CommonEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    @Column(nullable = false, name = "name")
    @NonNull
    private String name;

    @Column(nullable = false, name = "telnumber1")
    @NonNull
    private String telnumber1;

    @Column(name = "telnumber2")
    @NonNull
    private String telnumber2;

    @Column(name = "email")
    @NonNull
    private String email;

    @Column(name = "address")
    @NonNull
    private String address;


}
