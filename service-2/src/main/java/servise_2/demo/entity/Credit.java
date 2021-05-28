package servise_2.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Double fromAmount;

    private Double toAmount;

    private Double percent;

    public Credit(String name, Double fromAmount, Double toAmount, Double percent) {
        this.name = name;
        this.fromAmount = fromAmount;
        this.toAmount = toAmount;
        this.percent = percent;
    }
}
