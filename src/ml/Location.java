package ml;

/**
 * Created by fuat on 2/21/2017.
 */
public class Location {
    public String getClassLabel() {
        return classLabel;
    }

    private String classLabel;
    private double  distance;

    public double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "Location{" +
                "classLabel='" + classLabel + '\'' +
                ", distance=" + distance +
                '}';
    }

    public Location(String classLabel, double distance){
        this.classLabel = classLabel;
        this.distance = distance;

    }

}
