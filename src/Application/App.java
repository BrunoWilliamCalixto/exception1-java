package Application;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.entities.Reservation;
import model.exceptions.DomainExceptions;

public class App {
    //Tratamento de exceções

    public static void main(String[] args) throws ParseException {
        Scanner leia = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try{
            System.out.print("Room number: ");
            int roomNumber = leia.nextInt();
            System.out.print("Check-in date (DD/MM/YYYY): ");
            Date checkIn = sdf.parse(leia.next());
            System.out.print("Check-out date (DD/MM/YYYY): ");
            Date checkOut = sdf.parse(leia.next());

            Reservation reservation = new Reservation(roomNumber, checkIn, checkOut);
            System.out.println("Reservation " + reservation);
                
            System.out.println();
            System.out.println("Enter data to update the reservation: ");
            System.out.print("Check-in date (DD/MM/YYYY): ");
            checkIn = sdf.parse(leia.next());
            System.out.print("Check-out date (DD/MM/YYYY): ");
            checkOut = sdf.parse(leia.next());

            reservation.updateDates(checkIn, checkOut);
            System.out.println("Reservation: " + reservation);
        }
        catch(ParseException e){
            System.out.println("Invalid date format");
        }
        catch(DomainExceptions e){
            System.out.println("Error in reservation: " + e.getMessage());
        }
        catch(InputMismatchException e){
            System.out.println("Error: Please enter a valid number");
        }
        catch(RuntimeException e){
            System.out.println("Unexpected error");
        }

        leia.close();
    }
}
