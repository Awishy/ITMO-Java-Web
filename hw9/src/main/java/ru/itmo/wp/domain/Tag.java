package ru.itmo.wp.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(indexes = @Index(columnList = "name", unique = true))
public class Tag implements Comparable<Tag>{
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 32)
    private String name;

    public Tag() {
    }

    public Tag(@NotNull String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Tag o) {
        return this.name.compareTo(o.name);
    }
}
