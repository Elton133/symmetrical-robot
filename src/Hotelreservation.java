import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HotelReservation {
    static class Reservation {
        String name;
        String contactInfo;
        
        // Constructor for reservation information
        Reservation(String name, String contactInfo) {
            this.name = name;
            this.contactInfo = contactInfo;
        }
        
        // Overriding toString method to show reservation details
        @Override
        public String toString() {
            return "Name: " + name + ", Contact: " + contactInfo;
        }
    }
    
    public static void main(String[] args) {
        // HashMap to store room availability and reservation details
        HashMap<String, Reservation> myRooms = new HashMap<>();
        myRooms.put("101", null); // Null means the room is vacant
        myRooms.put("102", new Reservation("John Doe", "555-0123")); // Preoccupied by someone else
        myRooms.put("103", null);
        myRooms.put("104", null);

        Scanner scanner = new Scanner(System.in);
        
        // Main application loop
        while (true) {
            System.out.println("\n1. View Available Rooms\n2. Reserve a Room\n3. View Reservations\n4. Check Out\n5. Exit");
            int input = scanner.nextInt();
            scanner.nextLine();  // Consuming the newline

            switch (input) {
                case 1:
                    viewAvailableRooms(myRooms);
                    break;
                case 2:
                    reserveRoom(scanner, myRooms);
                    break;
                case 3:
                    viewReservation(scanner, myRooms);
                    break;
                case 4:
                    checkOut(scanner, myRooms);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return; // Exit the application
                default:
                    System.out.println("Invalid Option, Please try again.");
            }
        }
    }
    
    // View all rooms and their availability
    private static void viewAvailableRooms(HashMap<String, Reservation> myRooms) {
        System.out.println("ROOM AVAILABILITY:");
        myRooms.forEach((roomNumber, reservation) -> {
            if (reservation == null) {
                System.out.println("Room " + roomNumber + " is Vacant");
            } else {
                System.out.println("Room " + roomNumber + " is Occupied by " + reservation.name);
            }
        });
    }

    // Reserve a room if it's available
    private static void reserveRoom(Scanner scanner, HashMap<String, Reservation> myRooms) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your contact information: ");
        String contactInfo = scanner.nextLine();
        System.out.print("Enter the room number you want to reserve: ");
        String roomNumber = scanner.nextLine();

        // Check if the room exists
        if (myRooms.containsKey(roomNumber)) {
            // If room is vacant, proceed with reservation
            if (myRooms.get(roomNumber) == null) {
                myRooms.put(roomNumber, new Reservation(name, contactInfo)); // Make the reservation
                System.out.println("Room " + roomNumber + " reserved successfully for " + name);
            } else {
                System.out.println("Room " + roomNumber + " is already occupied by " + myRooms.get(roomNumber).name);
            }
        } else {
            System.out.println("Invalid Room number. Please enter a valid room number.");
        }
    }

    // View reservations by searching for a person's name and contact
    private static void viewReservation(Scanner scanner, HashMap<String, Reservation> myRooms) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your contact information: ");
        String contactInfo = scanner.nextLine();

        boolean foundReservation = false;
        // Search through all rooms for a matching reservation
        for (Map.Entry<String, Reservation> entry : myRooms.entrySet()) {
            Reservation reservation = entry.getValue();
            if (reservation != null && reservation.name.equals(name) && reservation.contactInfo.equals(contactInfo)) {
                System.out.println("You have reserved Room " + entry.getKey());
                foundReservation = true;
                break;
            }
        }

        if (!foundReservation) {
            System.out.println("No reservation found for " + name);
        }
    }

    // Check out and make the room vacant again
    private static void checkOut(Scanner scanner, HashMap<String, Reservation> myRooms) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your contact information: ");
        String contactInfo = scanner.nextLine();

        boolean foundCheckout = false;
        // Search for the reservation and clear it
        for (Map.Entry<String, Reservation> entry : myRooms.entrySet()) {
            Reservation reservation = entry.getValue();
            if (reservation != null && reservation.name.equals(name) && reservation.contactInfo.equals(contactInfo)) {
                myRooms.put(entry.getKey(), null); // Set the room to vacant
                System.out.println("You have successfully checked out from Room " + entry.getKey());
                foundCheckout = true;
                break;
            }
        }

        if (!foundCheckout) {
            System.out.println("No reservation found for " + name + ". Cannot check out.");
        }
    }
}
