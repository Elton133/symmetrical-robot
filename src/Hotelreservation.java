import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Hotelreservation {
    static String name;
    static String contactInfo;

    public static void main(String[] args) {
        // String myRooms[] = { ", Room 102 - Occupied, Room 103 - Vacant, Room 104 -
        // Vacant" };

        System.out.println("-----------------------------------------");
        System.out.println("WELCOME TO THE HOTEL RESERVATION SYSTEM!");
        System.out.println("-----------------------------------------");
        Scanner scanner = new Scanner(System.in);

        HashMap<String, String> myRooms = new HashMap<>();
        myRooms.put("101", "Vacant");
        myRooms.put("102", "Occupied");
        myRooms.put("103", "Vacant");
        myRooms.put("104", "Vacant");
        while (true) {

            System.out.println("1.View Available Rooms\n2.Reserve a Room\n3.View Reservations\n4.Check Out\n5.Exit");
            int input = scanner.nextInt();
            scanner.nextLine();

            switch (input) {
                case 1:
                    viewAvailableRooms(myRooms);
                    break;
                case 2:
                    reserveRooms(scanner, myRooms);
                    break;
                case 3:
                    viewReservations(scanner, myRooms);
                    break;
                case 4:
                    checkOutMenu(scanner, myRooms);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid Option, Please try again");

            }

        }
    }

    private static void viewAvailableRooms(HashMap<String, String> myRooms) {
        System.out.println("AVAILABLE ROOMS");
        myRooms.forEach((key, value) -> System.out.println("Room " + key + " Status: " + value));
    }

    private static void reserveRooms(Scanner scanner, HashMap<String, String> myRooms) {
        System.out.print("Enter Your name: ");
        // scanner.nextLine();
        name = scanner.nextLine();
        System.out.print("Enter Your contact information: ");
        contactInfo = scanner.nextLine();
        System.out.print("Enter the room number you want to reserve: ");
        String reserve = scanner.nextLine();
        if (myRooms.containsKey(reserve)) {
            if (myRooms.get(reserve).equals("Vacant")) {
                myRooms.put(reserve, "Occupied");
                System.out.println("Room " + reserve + " Reserved Successfully");
            } else {
                System.out.println("Room " + reserve + " is already occupied");
            }
        } else {
            System.out.println("Invalid Room number");
        }

    }

    private static void viewReservations(Scanner scanner, HashMap<String, String> myRooms) {
        System.out.println("VIEW RESERVATIOINS");
        System.out.print("ENTER NAME: ");
        String name1 = scanner.nextLine();
        System.out.print("ENTER CONTACT INFO: ");
        String contactInfo1 = scanner.nextLine();

        boolean hasReservation = false;
        for (Map.Entry<String, String> entry : myRooms.entrySet()) {
            if (entry.getValue().equals("Occupied") && !entry.getKey().equals("102")) {
                if (name1.equals(name) && contactInfo1.equals(contactInfo)) {
                    System.out.println("Room " + entry.getKey() + " is reserved for you");
                    hasReservation = true;
                }
            }
        }
        if (!hasReservation) {
            System.out.println("No reservation for you");
        }

    }

    private static void checkOutMenu(Scanner scanner, HashMap<String, String> myRooms) {
        System.out.println("CHECK OUT");
        System.out.print("ENTER NAME: ");
        String checkOutName = scanner.nextLine();
        System.out.print("ENTER CONTACT INFO: ");
        String checkOutInfo = scanner.nextLine();

        boolean hasCheckout = false;
        for (Map.Entry<String, String> entry : myRooms.entrySet()) {
            if (entry.getValue().equals("Occupied")) {
                if (checkOutName.equals(name) && checkOutInfo.equals(contactInfo) && !entry.getKey().equals("102")) {
                    System.out.println("Room " + entry.getKey() + " checked out sucessfully");
                    hasCheckout = true;
                }
            }
        }
        if (!hasCheckout) {
            System.out.println("No reservation for you to checkout");
        }

    }
}