package marat.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;


@Entity
@Table(name = "operation")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
public class Operation implements CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "toacc")
    @ToString.Exclude
    @NonNull
    private Account toacc;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fromacc")
    @ToString.Exclude
    @NonNull
    private Account fromacc;


    @Column(nullable = false, name = "sum")
    @NonNull
    private Long sum;

    @Column(nullable = false, name = "dayperiod")
    @NonNull
    private Timestamp dayperiod;


}

