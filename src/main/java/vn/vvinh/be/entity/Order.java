package vn.vvinh.be.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name =  "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    double total;
    String time;

    @OneToMany(mappedBy = "order")
    List<ServiceHistory> serviceHistories;

    @OneToMany(mappedBy = "order")
    List<PackageHistory> packageHistories;

    @ManyToOne
    @JoinColumn(name = "account_id")
    Account account;

    @OneToMany(mappedBy = "order")
            @JsonIgnore
    List<Transaction> transactionList;

}
