package project.spring.controllers;


import project.JavaFX;
import project.spring.repo.*;
import project.spring.models.*;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("api/theater")
public class TheaterController {

    private final ClientRepo clientRepo;
    private final TicketRepo ticketRepo;
    private final HallRepo hallRepo;
    private final SeatRepo seatRepo;
    private final PerformanceRepo performanceRepo;


    public TheaterController(ClientRepo clientRepo, TicketRepo ticketRepo, HallRepo hallRepo,
                             SeatRepo seatRepo, PerformanceRepo performanceRepo) {
        this.clientRepo = clientRepo;
        this.ticketRepo = ticketRepo;
        this.hallRepo = hallRepo;
        this.seatRepo = seatRepo;
        this.performanceRepo = performanceRepo;
    }



    @PostMapping("/tickets")
    Ticket createTicket(@RequestParam Integer price,@RequestParam Integer location, @RequestParam String type ) {
//        curl -X POST http://127.0.0.1:8080/api/theater/tickets?price=4238&location=9&type=A
        Ticket ticket = new Ticket(price);
        Client client = new Client("Николай", "Жуков", "86665314432", 70);
        Seat seat = new Seat(location, type);

        Date d = new Date();
        SimpleDateFormat date;
        SimpleDateFormat dateprem;
        SimpleDateFormat dateend;
        date = new SimpleDateFormat("01.01.2021 12:20");
        dateprem = new SimpleDateFormat("01.01.2021 10:00");
        dateend = new SimpleDateFormat("01.07.2021 20:00");

        date.format(d);
        dateprem.format(d);
        dateend.format(d);
        String date1 = date.format(d);
        String dateprem1 = dateprem.format(d);
        String dateend1 = dateend.format(d);

        Performance performance = new Performance("Мастер и Маргарита",
                dateprem1, dateend1, 16);
        Hall hall = new Hall("малый зал", date1);

        this.clientRepo.save(client);
        this.hallRepo.save(hall);

        seat.setHall(hall);
        this.seatRepo.save(seat);

        ticket.setClient(client);
        ticket.setSeat(seat);
        return this.ticketRepo.save(ticket);
    }

    @GetMapping("/tickets/{id}")
    Ticket getTicket(@PathVariable Long id) {
        System.out.println(id);
//        curl -X GET http://127.0.0.1:8080/api/theater/tickets/2
        return this.ticketRepo.findTicketById(id);
    }

    @GetMapping("/clients/{id}")
    Client getClient(@PathVariable Long id) {
        return this.clientRepo.findClientById(id);
    }

    public void setMain(JavaFX javaFX) {
    }

    @GetMapping("/clients/all")
    List<Client> getClients(){
        return this.clientRepo.findAll();
    }

    @GetMapping("/tickets/all")
    List<Ticket> getTickets(){
        return this.ticketRepo.findAll();
    }



    @DeleteMapping("/tickets")

    // http://127.0.0.1:8080/api/theater/tickets?id=..
    Ticket deleteTicket(@RequestParam Long id) {

        Ticket foundTicket = this.ticketRepo.findTicketById(id);
        this.ticketRepo.delete(foundTicket);
        return foundTicket;



    }


    @DeleteMapping("/clients")

        // http://127.0.0.1:8080/api/theater/clients?id=..
    Client deleteClient(@RequestParam Long id) {
        Client foundClient = this.clientRepo.findClientById(id);
        this.clientRepo.delete(foundClient);
        return foundClient;
    }

}
