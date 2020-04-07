import java.util.ArrayList;

public interface IInvoiceGenerator {
    double calculateFare(double distance, int time);
    double calculateFareForMultipleRides(Ride[] rides);
    InvoiceSummary getInvoiceSummary(Ride[] rides);
    void addRides(String userId);
    ArrayList<Ride> getRidesByUserId(String userId);
}
