/*
 * Thomas Acevedo
 * 
 * This code models various components like engine, freight cars> It 
 * allows the user to add freightcars to the train or delete and view trains 
 * characterists and value.
 * uses classes , abstract classes , and interfacces 
 * Object Oriented Prorgramming code.
 */
import java.util.Scanner;
import java.util.ArrayList;

public class Contents {
    private String _name;
    private double _density;
    private double _value;

    public Contents(String name, double density, double value) {
        _name = name;
        _density = density;
        _value = value;
    }

    public String getName() {
        return _name;
    }

    public double getDensity() {
        return _density;
    }

    public double getValue() {
        return _value;
    }

    public void setValue(double value) {
        _value = value;
    }

    public double getWeight(double loadFactor) {
        return _density * loadFactor;
    }

    public String toString() {
        return "Contents: name=" + _name + ", density=" + _density + ", value=" + _value;
    }
}

abstract class Container {
    private double _thickness;
    private double _density;

    public Container(double thickness, double density) {
        _thickness = thickness;
        _density = density;
    }

    public double getThickness() {
        return _thickness;
    }

    public double getDensity() {
        return _density;
    }

    public abstract double getInteriorVolume();

    public abstract double getExteriorVolume();

    public double getWallWeight() {
        double exteriorVolume = getExteriorVolume();
        double interiorVolume = getInteriorVolume();
        double wallVolume = exteriorVolume - interiorVolume;
        return _density * wallVolume;
    }

    public double getWeight() {
        return getWallWeight() + _thickness;
    }

    public String toString() {
        return "Container: thickness=" + _thickness + ", density=" + _density;
    }
}

class Cylinder extends Container {
    private double _radius;
    private double _length;

    public Cylinder(double thickness, double density, double radius, double length) {
        super(thickness, density);
        _radius = radius;
        _length = length;
    }

    public double getRadius() {
        return _radius;
    }

    public double getLength() {
        return _length;
    }

    @Override
    public double getInteriorVolume() {
        return Math.PI * Math.pow(_radius - getThickness(), 2) * (_length - (2 * getThickness()));
    }

    @Override
    public double getExteriorVolume() {
        return Math.PI * Math.pow(_radius, 2) * _length;
    }

    public String toString() {
        return "Cylinder: radius=" + _radius + ", length=" + _length + ", " + super.toString();
    }
}

class TrapezoidalBox extends Container {
    private double _height;
    private double _width;
    private double _upperLength;
    private double _lowerLength;

    public TrapezoidalBox(double thickness, double density, double height, double width, double upperLength,
            double lowerLength) {
        super(thickness, density);
        _height = height;
        _width = width;
        _upperLength = upperLength;
        _lowerLength = lowerLength;
    }

    public double getHeight() {
        return _height;
    }

    public double getWidth() {
        return _width;
    }

    public double getUpperLength() {
        return _upperLength;
    }

    public double getLowerLength() {
        return _lowerLength;
    }

    @Override
    public double getInteriorVolume() {
        double exteriorVolume = getExteriorVolume();
        double trapezoidArea = (computeTrapezoidArea(_upperLength, _width) + computeTrapezoidArea(_lowerLength, _width))
                * (_height - (2 * getThickness()));
        return exteriorVolume - trapezoidArea;
    }

    @Override
    public double getExteriorVolume() {
        return computeTrapezoidArea(_upperLength, _width) + computeTrapezoidArea(_lowerLength, _width) * _height;
    }

    private double computeTrapezoidArea(double length, double base) {
        return 0.5 * (length + base) * _height;
    }

    public String toString() {
        return "Trapezoidal Box: height=" + _height + ", width=" + _width +
                ", upperLength=" + _upperLength + ", lowerLength=" + _lowerLength + ", " + super.toString();
    }
}

abstract class RollingStock {
    private String _ownerName;
    private String _IDNumber;
    private double _baseFrameWeight;

    public RollingStock(String ownerName, String IDNumber, double baseFrameWeight) {
        _ownerName = ownerName;
        _IDNumber = IDNumber;
        _baseFrameWeight = baseFrameWeight;
    }

    public String getOwnerName() {
        return _ownerName;
    }

    public String getIDNumber() {
        return _IDNumber;
    }

    public double getBaseFrameWeight() {
        return _baseFrameWeight;
    }

    public abstract double getWeight();

    public String toString() {
        return "Rolling Stock: ownerName=" + _ownerName + ", IDNumber=" + _IDNumber + ", baseFrameWeight="
                + _baseFrameWeight;
    }
}

class Engine extends RollingStock {
    private int _pullingCapacity;

    public Engine(String ownerName, String identificationNumber, double baseFrameWeight, int pullingCapacity) {
        super(ownerName, identificationNumber, baseFrameWeight);
        _pullingCapacity = pullingCapacity;
    }

    public int getPullingCapacity() {
        return _pullingCapacity;
    }

    @Override
    public double getWeight() {
        return getBaseFrameWeight() + _pullingCapacity;
    }

    public String toString() {
        return "Engine: " + super.toString() + ", pullingCapacity=" + _pullingCapacity;
    }
}

class FreightCar extends RollingStock {
    private double _loadFactor;
    private Container _container;
    private Contents _contents;

    public FreightCar(String ownerName, String identificationNumber, double baseFrameWeight, double loadFactor,
            Container container, Contents contents) {
        super(ownerName, identificationNumber, baseFrameWeight);
        _loadFactor = loadFactor;
        _container = container;
        _contents = contents;
    }

    public double getLoadFactor() {
        return _loadFactor;
    }

    public void setLoadFactor(double loadFactor) {
        _loadFactor = loadFactor;
    }

    @Override
    public double getWeight() {
        return _container.getWeight() + _contents.getWeight(_loadFactor) + getBaseFrameWeight();
    }

    public double computeTotalValue() {
        return _contents.getValue() * _loadFactor;
    }

    public String toString() {
        return "Freight Car: " + super.toString() + ", loadFactor=" + _loadFactor +
                ", container=" + _container.toString() + ", contents=" + _contents.toString();
    }
}

class Train {
    private Engine _engine;
    private ArrayList<FreightCar> _freightCars;
    private String _engineerName;

    public Train(String engineerName, Engine engine) {
        _engineerName = engineerName;
        _engine = engine;
        _freightCars = new ArrayList<>();
    }

    public String getEngineerName() {
        return _engineerName;
    }

    public void addFreightCar(FreightCar freightCar) {
        _freightCars.add(freightCar);
    }

    public void deleteFreightCar(int index) {
        if (index >= 0 && index < _freightCars.size()) {
            _freightCars.remove(index);
        }
    }

    public double computeTotalWeight() {
        double totalWeight = _engine.getWeight();
        for (FreightCar freightCar : _freightCars) {
            totalWeight += freightCar.getWeight();
        }
        return totalWeight;
    }

    public double computeTotalValue() {
        double totalValue = 0;
        for (FreightCar freightCar : _freightCars) {
            totalValue += freightCar.computeTotalValue();
        }
        return totalValue;
    }

    public String toString() {
        String trainString = "Train: engineerName=" + _engineerName + "\nEngine: " + _engine.toString()
                + "\nFreight Cars:\n";
        for (FreightCar freightCar : _freightCars) {
            trainString += freightCar.toString() + "\n";
        }
        return trainString;
    }
}

public class ZaTrain {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int MAXIMUMLOAD;
        Train train = null;
        System.out.println("Enter Max load for a single car:");
        MAXIMUMLOAD = kb.nextInt();
        boolean exit = false;

        System.out.print("Enter the engine's name: ");
        kb.nextLine(); // consume the newline character
        String engineName = kb.nextLine();
        Engine engine = new Engine(engineName, "ID123", 100.0, 5000);

        System.out.print("Enter the engineer's name: ");
        String engineerName = kb.nextLine();
        train = new Train(engineerName, engine);

        do {
            System.out.println("\nTrain Building Menu");
            System.out.println("1. Add a car");
            System.out.println("2. Display train characteristics");
            System.out.println("3. Calculate total weight and value");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = kb.nextInt();
            if (choice == 1) {
                // Prompt user for details of car
                System.out.print("Enter the car's type (Cylinder/TrapezoidalBox): ");
                kb.nextLine(); // consume the newline character
                String carType = kb.nextLine();
                System.out.print("Enter the car's weight: ");
                double carWeight = kb.nextDouble();
                System.out.print("Enter the car's value: ");
                double carValue = kb.nextDouble();

                if ("Cylinder".equalsIgnoreCase(carType)) {
                    System.out.print("Enter the cylinder's radius: ");
                    double radius = kb.nextDouble();
                    System.out.print("Enter the cylinder's length: ");
                    double length = kb.nextDouble();
                    train.addFreightCar(new FreightCar(engineerName, "ID420", carWeight, MAXIMUMLOAD,
                            new Cylinder(carWeight, MAXIMUMLOAD, radius, length),
                            new Contents("Cylinder contents", MAXIMUMLOAD, carValue)));
                } else if ("TrapezoidalBox".equalsIgnoreCase(carType)) {
                    System.out.print("Enter the trapezoidal box's height: ");
                    double height = kb.nextDouble();
                    System.out.print("Enter the trapezoidal box's width: ");
                    double width = kb.nextDouble();
                    System.out.print("Enter the trapezoidal box's upper length: ");
                    double upperLength = kb.nextDouble();
                    System.out.print("Enter the trapezoidal box's lower length: ");
                    double lowerLength = kb.nextDouble();
                    train.addFreightCar(new FreightCar(engineerName, "ID69", carWeight, MAXIMUMLOAD,
                            new TrapezoidalBox(carWeight, MAXIMUMLOAD, height, width, upperLength, lowerLength),
                            new Contents("TrapezoidalBox contents", MAXIMUMLOAD, carValue)));
                } else {
                    System.out.println("car type not valid.Please try again");
                }
            } else if (choice == 2) {
                // Display train characteristics
                System.out.println(train.toString());
            } else if (choice == 3) {
                // Calculate total weight and value
                double totalWeight = train.computeTotalWeight();
                double totalValue = train.computeTotalValue();
                System.out.println("Total weight: " + totalWeight);
                System.out.println("Total value: " + totalValue);
            } else if (choice == 4) {
                // Exit the program
                exit = true;
            } else {
                System.out.println("Choice not found. Please try again.");
            }
        } while (!exit);

        kb.close();
    }
}
