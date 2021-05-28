package servise_2.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private String passport;

    private Double salary;

    private Double creditAmount;
}
