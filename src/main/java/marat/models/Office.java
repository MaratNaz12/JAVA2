package marat.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "office")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
public class Office implements CommonEntity {

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

    @Column(nullable = false, name = "telnumber2")
    @NonNull
    private String telnumber2;

    @Column(nullable = false, name = "email")
    @NonNull
    private String email;

    @Column(nullable = false, name = "address")
    @NonNull
    private String address;


}
