package com.zup.ecommerce.pagamento;

import com.zup.ecommerce.compra.Compra;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Compra compra;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    @PastOrPresent
    private LocalDateTime dataProcessamento;

    public Pagamento() {
    }

    public Pagamento(Compra compra, StatusPagamento status) {
        this.compra = compra;
        this.status = status;
        this.dataProcessamento = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Compra getCompra() {
        return compra;
    }

    public StatusPagamento getStatus() {
        return status;
    }
}
