public class FjordFuelEfficiency {

    public static void main(String[] args) {
        
        Vehicle takk = new Vehicle("Takk", "Sedan", 40, 9.5);
        Vehicle beklager = new Vehicle("Beklager", "Sedan", 45, 7.5);
        Vehicle vakker = new Vehicle("Vakker", "SUV", 60, 7.5);
        Vehicle stygg = new Vehicle("Stygg", "SUV", 50, 9.0);
        Vehicle vanskellig = new Vehicle("Vanskellig", "Truck", 60, 8.75);
        Vehicle lastebil = new Vehicle("Lastebil", "Truck", 70, 5.5);

        Vehicle[] vehicles = {takk, beklager, vakker, stygg, vanskellig, lastebil};

        for (int i = 0; i < vehicles.length; i++) {

            Vehicle v = vehicles[i];

            double fuelEfficiency = v.fuelEfficiency(v.getTankSize(), v.getTime());
            boolean meetsGovernmentStandards = v.meetsGovernmentStandards(v.getType(), fuelEfficiency);

            if (meetsGovernmentStandards) {

                System.out.println(v.getModel() + " meets government standards.");
                System.out.println("It had a fuel efficiency of: " + fuelEfficiency + " mpg.\n");

            } else {

                System.out.println(v.getModel() + " does not meet government standards.");
                System.out.println("It had a fuel efficiency of: " + fuelEfficiency + " mpg.\n");

            }

        }

    }

}