import org.junit.Assert;
import org.junit.Test;

public class InvoiceGeneratorTest {
    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFareForJourney() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        double distance = 3.0;
        int time = 10;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(40, fare, 0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinimumFare() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        double distance = 0.1;
        int time = 2;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(5, fare, 0);
    }

    @Test
    public void givenMultipleRideDetails_ShouldReturnTotalFare() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        Ride[] rides = {new Ride(4.0, 15), new Ride(3.0, 11), new Ride(6.0, 25)};
        double totalFare = invoiceGenerator.calculateFareForMultipleRides(rides);
        Assert.assertEquals(181, totalFare, 0);
    }
}
