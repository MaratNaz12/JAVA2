package marat.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "bankacctype")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class BankAccType implements CommonEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    @Column(nullable = false, name = "name")
    @NonNull
    private String name;

    @Column(nullable = false, name = "profit")
    @NonNull
    private Double profit;

    @Column(nullable = false, name = "dayperiod")
    @NonNull
    private Long dayperiod;

    @Column(nullable = false, name = "maxdebit")
    @NonNull
    private Long maxdebit;

    @Column(nullable = false, name = "mindebit")
    @NonNull
    private Long mindebit;

    @Column(nullable = false, name = "maxdaccrual")
    @NonNull
    private Long maxaccruel;

    @Column(nullable = false, name = "minaccrual")
    @NonNull
    private Long minaccrual;
}
