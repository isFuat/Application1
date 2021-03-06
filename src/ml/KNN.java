package ml;

import java.io.*;
import java.util.*;

/**
 * Created by fuat on 2/20/2017.
 */
public class KNN {

    public static void main(String args[]){



        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("World_Cities_Location_table.csv")))){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter x and y coordinates, and k: ");
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();
            //k from scanner.
            int k = scanner.nextInt();
            System.out.println();
            String line;
            ArrayList<Location> arrayList = new ArrayList<Location>();;
            while ((line = bufferedReader.readLine()) != null){

                //System.out.println(line.split(";")[1]);
                String[] elements = line.split(";");
                String classes = elements[1].trim().replaceAll("\"", "");
                double x2 = Double.parseDouble(elements[3].replaceAll("\"", ""));
                double y2 = Double.parseDouble(elements[4].replaceAll("\"", ""));


                double distance = calculateDistance(x, x2, y, y2);

                arrayList.add(new Location(classes, distance));


            }

            sortLocation(arrayList);

            Map<String, Integer> stringIntegerMap = new HashMap<String, Integer>();
            for (int i = 0; i < k; i ++){
                String key = arrayList.get(i).getClassLabel();
                if (stringIntegerMap.containsKey(key)){
                    stringIntegerMap.put(key,stringIntegerMap.get(key) + 1 );
                }else{
                    stringIntegerMap.put(key, 1);
                }

                System.out.println(arrayList.get(i).getClassLabel());

            }

            System.out.println("Predicted location: "+estimatedClass(stringIntegerMap));

            /*for (String key: hashMap.keySet()){
                System.out.println("Key: "+key+", Value: "+hashMap.get(key));
            }*/

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static String estimatedClass(Map<String, Integer> stringIntegerMap) {
        if (stringIntegerMap.size() == 0) {
            throw new IllegalArgumentException();
        }
        int max = 0;
        String nearestClass = null;
        for (String s: stringIntegerMap.keySet()){
            if (stringIntegerMap.get(s) > max){
                max = stringIntegerMap.get(s);
                nearestClass = s;
            }
        }
        return nearestClass;
    }

    private static void sortLocation(ArrayList<Location> arrayList) {
        Collections.sort(arrayList, new Comparator<Location>() {
            @Override
            public int compare(Location location1, Location location2) {

                if (location1.getDistance() > location2.getDistance()){
                    return 1;
                }else if (location1.getDistance() < location2.getDistance()){
                    return -1;
                }else {
                    return 0;
                }

            }
        });
    }

    private static double calculateDistance(double x, double x2, double y, double y2) {

        return Math.sqrt(Math.pow((x - x2), 2) + Math.pow((y - y2), 2));
    }


}
