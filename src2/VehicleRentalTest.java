import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

import java.lang.reflect.Modifier;
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
	@Test
	public void testRentAndReturnVehicle() {
	    RentalSystem system = RentalSystem.getInstance();

	    Vehicle car = new Car("Aston Martin", "DB5", 1964, 4);
	    car.setLicensePlate("NBJ117");

	    Customer customer = new Customer(007, "James Bond");

	    assertTrue(system.addVehicle(car));
	    assertTrue(system.addCustomer(customer));

	    assertEquals(Vehicle.VehicleStatus.AVAILABLE, car.getStatus());

	    boolean rentResult = system.rentVehicle(car, customer, LocalDate.now(), 100.0);
	    assertTrue(rentResult, "Rent should work");
	    assertEquals(Vehicle.VehicleStatus.RENTED, car.getStatus());

	    boolean rentAgain = system.rentVehicle(car, customer, LocalDate.now(), 100.0);
	    assertFalse(rentAgain, "Renting a rented vehicle should not work");

	    boolean returnResult = system.returnVehicle(car, customer, LocalDate.now(), 0.0);
	    assertTrue(returnResult, "Return should work");
	    assertEquals(Vehicle.VehicleStatus.AVAILABLE, car.getStatus());

	    boolean returnAgain = system.returnVehicle(car, customer, LocalDate.now(), 0.0);
	    assertFalse(returnAgain, "Returning an available vehicle should not work");
	}
	@Test
	public void testSingletonRentalSystem() throws Exception {
	    Constructor<RentalSystem> constructor = RentalSystem.class.getDeclaredConstructor();

	    int modifiers = constructor.getModifiers();
	    assertTrue(Modifier.isPrivate(modifiers), "Constructor should be private so it enforces singleton behaviour.");

	    RentalSystem instance = RentalSystem.getInstance();
	    assertNotNull(instance, "getInstance() should be returning a non-null RentalSystem instance.");
	}
}