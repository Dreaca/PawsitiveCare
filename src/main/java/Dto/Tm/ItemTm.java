package Dto.Tm;

import lombok.*;

@NoArgsConstructor
@ToString
@AllArgsConstructor
@Setter
@Getter
public class ItemTm {
    private String itemCode;
    private String description;
    private int QTO;
    private double unitPrice;
}
