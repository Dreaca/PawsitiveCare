package Dto;

import javafx.scene.image.Image;
import lombok.*;

import java.awt.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class EmployeeDto {
        private String empId;
        private String name;
        private String address;
        private String contact;
        private double salary;
        private  String userId;
        private String NIC;
}
