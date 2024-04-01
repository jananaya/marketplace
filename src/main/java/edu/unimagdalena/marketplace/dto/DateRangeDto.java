package edu.unimagdalena.marketplace.dto;

import edu.unimagdalena.marketplace.constant.ValidationMessage;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DateRangeDto {
    @NotBlank(message = ValidationMessage.StartDateIsBlank)
    private String startDate;
    @NotBlank(message = ValidationMessage.EndDateIsBlank)
    private String endDate;
}
