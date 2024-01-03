package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import entity.Customer;
import entity.Lease;
import entity.Vehicle;

class ICarLeaseRepositoryImplTest {
	static ICarLeaseRepositoryImpl repo;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		repo = new ICarLeaseRepositoryImpl();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		repo = null;
	}

	@Test
	void testICarLeaseRepositoryImpl() {
		ICarLeaseRepositoryImpl carLeaseRepository = new ICarLeaseRepositoryImpl();
		assertNotNull(carLeaseRepository,"ICarLeaseRepositoryImpl instance should not be null");
	}
	
	@Test
    public void testCarCreation() {
        ICarLeaseRepository carLeaseRepository = new ICarLeaseRepositoryImpl();
        Vehicle car = new Vehicle(17,"Toyota", "Camry", 2022, 50.0, "available", 5, 2000);
        carLeaseRepository.addCar(car);
    }
	@Test
	public void testLeaseCreation() {
        ICarLeaseRepository carLeaseRepository = new ICarLeaseRepositoryImpl();
        Customer customer = new Customer(13,"John", "Doe", "john.doe@example.com", "1234567890");
        carLeaseRepository.addCustomer(customer);
        
        Vehicle car = new Vehicle(15,"Toyota", "Camry", 2022, 50.0, "available", 5, 2000);
        carLeaseRepository.addCar(car);

        Date startDate=Date.valueOf("2023-11-28");
        Date endDate=Date.valueOf("2023-12-25");
		Lease lease = carLeaseRepository.createLease(13, 15, startDate, endDate);

        // Verify if the lease is successfully created
        assertNotNull(lease);
    }
	@Test
    public void testLeaseRetrieval() {
        ICarLeaseRepository carLeaseRepository = new ICarLeaseRepositoryImpl();
        Lease retrievedLease = carLeaseRepository.listActiveLeases().get(0);

        // Verify if the lease is successfully retrieved
        assertNotNull(retrievedLease);
    }
	@Test
    public void testCarNotFoundException() {
        ICarLeaseRepository carLeaseRepository = new ICarLeaseRepositoryImpl();
        carLeaseRepository.findCarById(-1);  // Invalid car ID, should throw CarNotFoundException
    }

    @Test
    public void testLeaseNotFoundException() {
        ICarLeaseRepository carLeaseRepository = new ICarLeaseRepositoryImpl();
        carLeaseRepository.returnCar(-1);  // Invalid lease ID, should throw LeaseNotFoundException
    }

    @Test
    public void testCustomerNotFoundException() {
        ICarLeaseRepository carLeaseRepository = new ICarLeaseRepositoryImpl();
        carLeaseRepository.findCustomerById(-1);  // Invalid customer ID, should throw CustomerNotFoundException
    }

}
