package carsharing.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Customer {

    private final int id;
    private final String name;
    Car rentedCar;

}
