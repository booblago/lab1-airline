package ua.kpi.ips32.airline.company;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.kpi.ips32.airline.model.Aircraft;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AirlineCompanyMockitoTest {

    @Mock
    private Aircraft first;

    @Mock
    private Aircraft second;

    @Test
    void totalsUsePolymorphicMethodsFromMocks() {
        when(first.getPassengerCapacity()).thenReturn(100);
        when(first.getCargoCapacityTons()).thenReturn(10.0);
        when(second.getPassengerCapacity()).thenReturn(50);
        when(second.getCargoCapacityTons()).thenReturn(20.5);

        AirlineCompany company = new AirlineCompany("Mock Air");
        company.addAircraft(first);
        company.addAircraft(second);

        assertEquals(150, company.getTotalPassengerCapacity());
        assertEquals(30.5, company.getTotalCargoCapacityTons(), 0.0001);

        verify(first).getPassengerCapacity();
        verify(second).getPassengerCapacity();
        verify(first).getCargoCapacityTons();
        verify(second).getCargoCapacityTons();
    }

    @Test
    void findByFuelUsesMockedFuelValues() {
        when(first.getFuelConsumptionPerHour()).thenReturn(3.2);
        when(second.getFuelConsumptionPerHour()).thenReturn(11.0);
        when(first.getModel()).thenReturn("Small");

        AirlineCompany company = new AirlineCompany("Mock Air");
        company.addAircraft(first);
        company.addAircraft(second);

        List<Aircraft> found = company.findByFuelConsumption(1.0, 5.0);

        assertEquals(1, found.size());
        assertEquals("Small", found.get(0).getModel());
    }
}
