import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VehicleRentalTest {

	@Test
	public void testLicensePlateValidation() {
	    Vehicle valid1 = new Car("Toyota", "Corolla", 2020, 4);
	    Vehicle valid2 = new Car("Honda", "Civic", 2021, 4);
	    Vehicle valid3 = new Car("Ford", "Focus", 2022, 4);

	    
	    assertDoesNotThrow(() -> valid1.setLicensePlate("AAA100"));
	    assertDoesNotThrow(() -> valid2.setLicensePlate("ABC567"));
	    assertDoesNotThrow(() -> valid3.setLicensePlate("ZZZ999"));

	    
	    Vehicle invalid1 = new Car("Chevy", "Malibu", 2020, 4);
	    Vehicle invalid2 = new Car("Toyota", "Camry", 2021, 4);
	    Vehicle invalid3 = new Car("Cadillac", "XT4", 2022, 4);
	    Vehicle invalid4 = new Car("Ram", "Rebel", 2023, 4);

	    assertThrows(IllegalArgumentException.class, () -> invalid1.setLicensePlate(""));
	    assertThrows(IllegalArgumentException.class, () -> invalid2.setLicensePlate(null));
	    assertThrows(IllegalArgumentException.class, () -> invalid3.setLicensePlate("AAA1000"));
	    assertThrows(IllegalArgumentException.class, () -> invalid4.setLicensePlate("ZZZ99"));
	}
}