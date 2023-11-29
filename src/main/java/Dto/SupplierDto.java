package Dto;

import lombok.*;

@NoArgsConstructor
@ToString
@Setter
@Getter
@AllArgsConstructor
public class SupplierDto {
    private String suppId;
    private String name;
    private String type;
    private String location;
    private String contact;
}
