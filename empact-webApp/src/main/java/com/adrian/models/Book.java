package com.adrian.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;
    @Column(name = "author")
    private String author;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "editor")
    private String editor;

    @Column(name = "price")
    private int price;


    @Column(name = "units_in_stock")
    private int stock;


}
