package project.spring.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javafx.collections.ObservableList;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    /*
    Соединяю ticket и client
     */

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    private Integer price;

    public Ticket( Integer price) {
        this.price = price;
    }

    public Ticket(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", client=" + client +
                ", seat=" + seat +
                ", price=" + price +
                '}';
    }
}
