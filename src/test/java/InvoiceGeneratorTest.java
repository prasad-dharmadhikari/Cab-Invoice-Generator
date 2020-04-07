import org.junit.Assert;
import org.junit.Test;

public class InvoiceGeneratorTest {

    InvoiceGeneratorService invoiceForNormalPlan = new InvoiceGeneratorService(InvoiceGeneratorService.subscriptionPlan.NORMAL);
    InvoiceGeneratorService invoiceForPremiumPlan = new InvoiceGeneratorService(InvoiceGeneratorService.subscriptionPlan.PREMIUM);

    @Test
    public void givenDistanceAndTime_WithNormalSubscriptionPlan_ShouldReturnTotalFareForJourney() {
        double distance = 3.0;
        int time = 10;
        double fare = invoiceForNormalPlan.calculateFare(distance, time);
        Assert.assertEquals(40, fare, 0);
    }

    @Test
    public void givenLessDistanceAndTime_WithNormalSubscriptionPlan_ShouldReturnMinimumFare() {
        double distance = 0.1;
        int time = 2;
        double fare = invoiceForNormalPlan.calculateFare(distance, time);
        Assert.assertEquals(5, fare, 0);
    }

    @Test
    public void givenMultipleRideDetails_WithNormalSubscriptionPlan_ShouldReturnTotalFare() {
        Ride[] rides = {new Ride(4.0, 15), new Ride(3.0, 11), new Ride(6.0, 25)};
        double totalFare = invoiceForNormalPlan.calculateFareForMultipleRides(rides);
        Assert.assertEquals(181, totalFare, 0);
    }

    @Test
    public void givenMultipleRideDetails_WithNormalSubscriptionPlan_ShouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(4.0, 15), new Ride(3.0, 11), new Ride(6.0, 25)};
        InvoiceSummary invoiceSummary = invoiceForNormalPlan.getInvoiceSummary(rides);
        InvoiceSummary expectedSummary = new InvoiceSummary(3,181.0);
        Assert.assertEquals(expectedSummary, invoiceSummary);
    }

    @Test
    public void givenUserId_WithNormalSubscriptionPlan_ShouldReturnListOfRidesAndInvoice() {
        String userId= "a@b.com";
        Ride[] rides = {new Ride(4.0, 15), new Ride(3.0, 11), new Ride(6.0, 25)};
        invoiceForNormalPlan.addRides(userId);
        InvoiceSummary invoiceSummary = invoiceForNormalPlan.getInvoiceSummary(rides);
        InvoiceSummary expectedSummary = new InvoiceSummary(3,181.0);
        Assert.assertEquals(expectedSummary, invoiceSummary);
        Assert.assertEquals(rides.length, invoiceForNormalPlan.getRidesByUserId(userId).size());
    }

    @Test
    public void givenDistanceAndTime_WithPremiumSubscriptionPlan_ShouldReturnTotalFareForJourney() {
        double distance = 3.0;
        int time = 10;
        double fare = invoiceForPremiumPlan.calculateFare(distance, time);
        Assert.assertEquals(65, fare, 0);
    }

    @Test
    public void givenLessDistanceAndTime_WithPremiumSubscriptionPlan_ShouldReturnMinimumFare() {
        double distance = 0.1;
        int time = 2;
        double fare = invoiceForPremiumPlan.calculateFare(distance, time);
        Assert.assertEquals(20, fare, 0);
    }

    @Test
    public void givenMultipleRideDetails_WithPremiumSubscriptionPlan_ShouldReturnTotalFare() {
        Ride[] rides = {new Ride(4.0, 15), new Ride(3.0, 11), new Ride(6.0, 25)};
        double totalFare = invoiceForPremiumPlan.calculateFareForMultipleRides(rides);
        Assert.assertEquals(297, totalFare, 0);
    }

    @Test
    public void givenMultipleRideDetails_WithPremiumSubscriptionPlan_ShouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(4.0, 15), new Ride(3.0, 11), new Ride(6.0, 25)};
        InvoiceSummary invoiceSummary = invoiceForPremiumPlan.getInvoiceSummary(rides);
        InvoiceSummary expectedSummary = new InvoiceSummary(3,297.0);
        Assert.assertEquals(expectedSummary, invoiceSummary);
    }

    @Test
    public void givenUserId_WithPremiumSubscriptionPlan_ShouldReturnListOfRidesAndInvoice() {
        String userId= "abc@b.com";
        Ride[] rides = {new Ride(4.0, 15), new Ride(3.0, 11), new Ride(6.0, 25)};
        invoiceForPremiumPlan.addRides(userId);
        InvoiceSummary invoiceSummary = invoiceForPremiumPlan.getInvoiceSummary(rides);
        InvoiceSummary expectedSummary = new InvoiceSummary(3,297.0);
        Assert.assertEquals(expectedSummary, invoiceSummary);
        Assert.assertEquals(rides.length, invoiceForPremiumPlan.getRidesByUserId(userId).size());
    }
}
