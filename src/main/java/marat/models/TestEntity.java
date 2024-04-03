package marat.models;

import jakarta.persistence.*;
import lombok.*;
import marat.models.CommonEntity;


@Entity
@Table(name = "testentity")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
public class TestEntity implements CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    @Column(nullable = false, name = "name")
    @NonNull
    private String name;


}
