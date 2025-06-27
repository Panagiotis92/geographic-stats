package com.pkoll.geographic_stats.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "languages")
public class Language {
    @Id
    @Column(name = "language_id", nullable = false)
    private Integer id;

    @Column(name = "language", nullable = false, length = 50)
    private String language;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}