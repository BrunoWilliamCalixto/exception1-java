package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainExceptions;

public class Reservation {
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private Integer roomNumber;
    private Date checkIn;
    private Date checkOut;

    public Reservation() {
    }

    public Reservation(Integer roomNumber, Date checkIn, Date checkOut) throws DomainExceptions{
        if (!checkOut.after(checkIn)) {
            throw new DomainExceptions( "Check-out date must be after check-in date");
        }
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckin() {
        return checkIn;
    }

    public Date getCheckout() {
        return checkOut;
    }

    public long duration() {
        long diff = checkOut.getTime() - checkIn.getTime(); // Aqui pegamos a diferença entre as datas em milisegundos com a variavel do tipo long
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS); // Aqui convertemos os milisegundos em dias, chamando a variável diff, para a conversão para dias
    }
    
    public void updateDates(Date checkIn, Date checkOut) throws DomainExceptions{

        Date now = new Date();
        if (checkIn.before(now) || checkOut.before(now)) {
            throw new DomainExceptions("Reservation dates for updates must be future dates."); //Exceção do java onde usamos quando o argumento do método é inválido
        } if (!checkOut.after(checkIn)) {
            throw new DomainExceptions( "Check-out date must be after check-in date");
        }
        this.checkIn = checkIn;
        this.checkOut = checkOut; // recebe as datas que vieram como argumento, sobrepondo a variável
    }

    @Override
    public String toString() {
        return "Room "
                + roomNumber
                + ", check-in: "
                + sdf.format(checkIn)
                + ", check-out: "
                + sdf.format(checkOut)
                + ", "
                + duration()
                + " nights.";
    }

}
