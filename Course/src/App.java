import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.Reservation;

public class App {
    
    //************Evolução sobre tratamento de exceções (Solução Muito Ruim)*************
    
    public static void main(String[] args) throws ParseException {
        Scanner leia = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("Room number: ");
        int roomNumber = leia.nextInt();
        System.out.print("Check-in date (DD/MM/YYYY): ");
        Date checkIn =  sdf.parse(leia.next());
        System.out.print("Check-out date (DD/MM/YYYY): ");
        Date checkOut =  sdf.parse(leia.next());

        if(!checkOut.after(checkIn)){
            System.out.println("Error in reservation: Check-out date must be after check-in date");
        }else{
            Reservation reservation = new Reservation(roomNumber, checkIn, checkOut);
            System.out.println("Reservation " + reservation);

            System.out.println();
            System.out.print("Enter data to update the reservation: ");
            System.out.print("Check-in date (DD/MM/YYYY): ");
            checkIn =  sdf.parse(leia.next());
            System.out.print("Check-out date (DD/MM/YYYY): ");
            checkOut =  sdf.parse(leia.next());

            Date now = new Date();
            if(checkIn.before(now) || checkOut.before(now)){
                System.out.println("Error in reservation: Reservation dates for updates must be future dates.");
            }else if(!checkOut.after(checkIn)){
                System.out.println("Error in reservation: Check-out date must be after check-in date");
            }else{
                reservation.updateDates(checkIn, checkOut);
                System.out.println("Reservation " + reservation);
            }
        }

        


        leia.close();

    }
}
