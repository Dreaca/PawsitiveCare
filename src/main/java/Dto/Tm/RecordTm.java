package Dto.Tm;

import javafx.fxml.FXML;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RecordTm {
    private String petId;
    private String recordId;
    private String date;
    private String description;
}
