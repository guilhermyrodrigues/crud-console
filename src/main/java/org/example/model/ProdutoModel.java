package org.example.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "produtos")
public class ProdutoModel {

    @Id
    private Integer id;

    @Column
    private String nome;

    @Column
    private Double preco;
}
