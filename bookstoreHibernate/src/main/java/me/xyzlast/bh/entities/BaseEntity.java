package me.xyzlast.bh.entities;

import javax.persistence.*;

/**
 * Created by ykyoon on 2/27/14.
 */
@MappedSuperclass
public class BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
