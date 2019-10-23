public class Vehicle {

    private String model;
    private String type;
    private double tankSize;
    private double time;

    public Vehicle (String model, String type, int tankSize, double time) {

        this.model = model;
        this.type = type;
        this.tankSize = tankSize / 3.785; //Convert from L to Gallons
        this.time = time;

    }

    public double fuelEfficiency(double tankSize, double time) {

        return (60 * time) / tankSize;

    }

    public boolean meetsGovernmentStandards(String type, double fuelEfficiency) {

        if (fuelEfficiency > governmentStandards(type)) {
            return true;
        } else {
            return false;
        }

    }

    private int governmentStandards(String type) {

        if (type.equals("Sedan")) return 50;
        if (type.equals("SUV")) return 40;
        if (type.equals("Truck")) return 30;
        return 0;

    }

    public double getTankSize() {
        return this.tankSize;
    }

    public double getTime() {
        return this.time;
    }

    public String getModel() {
        return this.model;
    }

    public String getType() {
        return this.type;
    }

}