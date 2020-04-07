import java.util.ArrayList;

public class InvoiceGeneratorService implements IInvoiceGenerator {

    private double RATE_PER_KM;
    private int RATE_PER_MIN;
    private double MINIMUM_FARE;

    public enum subscriptionPlan {NORMAL, PREMIUM}


    RideRepository rideRepository = new RideRepository();
    ArrayList<Ride> listOfRides = new ArrayList<Ride>();

    public InvoiceGeneratorService(InvoiceGeneratorService.subscriptionPlan plan) {
        if (plan.equals(subscriptionPlan.NORMAL)) {
            this.RATE_PER_KM = 10;
            this.RATE_PER_MIN = 1;
            this.MINIMUM_FARE = 5;
        }
        if (plan.equals(subscriptionPlan.PREMIUM)) {
            this.RATE_PER_KM = 15;
            this.RATE_PER_MIN = 2;
            this.MINIMUM_FARE = 20;
        }
    }

    @Override
    public double calculateFare(double distance, int time) {
        double totalFare = RATE_PER_KM * distance + RATE_PER_MIN * time;
        return Math.max(totalFare, MINIMUM_FARE);
    }

    @Override
    public double calculateFareForMultipleRides(Ride[] rides) {
        double aggregateFare = 0;
        for (Ride ride : rides) {
            listOfRides.add(ride);
            aggregateFare = aggregateFare + calculateFare(ride.distance, ride.time);
        }
        return aggregateFare;
    }

    @Override
    public InvoiceSummary getInvoiceSummary(Ride[] rides) {
        return new InvoiceSummary(rides.length, calculateFareForMultipleRides(rides));
    }

    @Override
    public void addRides(String userId) {
        rideRepository.addUserRides(userId, listOfRides);
    }

    @Override
    public ArrayList<Ride> getRidesByUserId(String userId) {
        ArrayList<Ride> ridesByUserId = rideRepository.getRidesByUserId(userId);
        return ridesByUserId;
    }
}