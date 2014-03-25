package com.rushdevo.keydroid.app.db.model;

public class User  {
    private Integer id;
    private String keybase_id;

    public User(Integer id, String keybase_id) {
        this.id = id;
        this.keybase_id = keybase_id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeybaseID() {
        return this.keybase_id;
    }
}
