import java.util.*;

public class ParkingLot {
    int MAX_SIZE = 0;
    private class Car {
        String regNo;
        Integer age;
        public Car(String regNo, Integer age) {
            this.regNo = regNo;
            this.age = age;
        }
    }
    // Available slots list
    ArrayList<Integer> availableSlotList;
    // Map of Slot, Car
    Map<String, Car> slotCarMap;
    // Map of RegNo, Slot
    Map<String, String> regNoCarMap;
    // Map of Age, List of RegNo
    Map<Integer, ArrayList<String>> ageCarMap;


    public void createParkingLot(String lotCount) {
        try {
            this.MAX_SIZE = Integer.parseInt(lotCount);
        } catch (Exception e) {
            System.out.println("Invalid lot count");
        }
        this.availableSlotList = new ArrayList<Integer>() {};
        for (int i=1; i<= this.MAX_SIZE; i++) {
            availableSlotList.add(i);
        }
        this.slotCarMap = new HashMap<String, Car>();
        this.regNoCarMap = new HashMap<String, String>();
        this.ageCarMap = new HashMap<Integer, ArrayList<String>>();
        System.out.println("Created parking of " + lotCount + " slots");
    }
    public void park(String regNo, Integer age) {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
        } else if (this.slotCarMap.size() == this.MAX_SIZE) {
            System.out.println("Sorry, parking lot is full");
        } else {
            Collections.sort(availableSlotList);
            String slot = availableSlotList.get(0).toString();
            Car car = new Car(regNo, age);
            this.slotCarMap.put(slot, car);
            this.regNoCarMap.put(regNo, slot);
            if (this.ageCarMap.containsKey(age)) {
                ArrayList<String> regNoList = this.ageCarMap.get(age);
                this.ageCarMap.remove(age);
                regNoList.add(regNo);
                this.ageCarMap.put(age, regNoList);
            } else {
                ArrayList<String> regNoList = new ArrayList<String>();
                regNoList.add(regNo);
                this.ageCarMap.put(age, regNoList);
            }
            System.out.println("Car with vehicle registration number " +  "\"" +regNo + "\"" + " has been parked at slot number " + slot);
            availableSlotList.remove(0);
        }
    }
    public void leave(String slotNo) {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
        } else if (this.slotCarMap.size() > 0) {
            Car carToLeave = this.slotCarMap.get(slotNo);
            if (carToLeave != null) {
                this.slotCarMap.remove(slotNo);
                this.regNoCarMap.remove(carToLeave.regNo);
                ArrayList<String> regNoList = this.ageCarMap.get(carToLeave.age);
                if (regNoList.contains(carToLeave.regNo)) {
                    regNoList.remove(carToLeave.regNo);
                }
                // Add the Lot No. back to available slot list.
                this.availableSlotList.add(Integer.parseInt(slotNo));
                System.out.println("Slot number " + slotNo + " vacated, the car with vehicle registration number " + "\"" + carToLeave.regNo + "\"" + " left the space, the driver of the car was of age " + carToLeave.age);
            }
        } else {
            System.out.println("Parking lot is empty");
        }
    }

    public void getRegistrationNumbersFromAge(String age) {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
        } else if (this.ageCarMap.containsKey(Integer.valueOf(age))) {
            ArrayList<String> regNoList = this.ageCarMap.get(Integer.valueOf(age));
            for (int i=0; i < regNoList.size(); i++) {
                if (!(i==regNoList.size() - 1)){
                    System.out.print(regNoList.get(i) + ",");
                } else {
                    System.out.print(regNoList.get(i));
                }
            }
            System.out.println();
        }
    }
    public void getSlotNumbersFromAge(String age) {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
        } else if (this.ageCarMap.containsKey(Integer.valueOf(age))) {
            ArrayList<String> regNoList = this.ageCarMap.get(Integer.valueOf(age));
            ArrayList<Integer> slotList = new ArrayList<Integer>();
            for (int i=0; i < regNoList.size(); i++) {
                slotList.add(Integer.valueOf(this.regNoCarMap.get(regNoList.get(i))));
            }
            Collections.sort(slotList);
            for (int j=0; j < slotList.size(); j++) {
                if (!(j == slotList.size() - 1)) {
                    System.out.print(slotList.get(j) + ",");
                } else {
                    System.out.print(slotList.get(j));
                }
            }
            System.out.println();
        } else {
            System.out.println();
        }
    }
    public void getSlotNumberFromRegNo(String regNo) {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
        } else if (this.regNoCarMap.containsKey(regNo)) {
            System.out.println(this.regNoCarMap.get(regNo));
        } else {
            System.out.println();
        }
    }
}
