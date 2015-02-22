package com.martynovich.hotel.domain;

/**
 * @author
 */
public class Apartment {
    private long id;
    private short number;
    private byte rooms;
    private boolean free;
    private short price;
    private byte level;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public short getNumber() {
        return number;
    }

    public void setNumber(short number) {
        this.number = number;
    }

    public byte getRooms() {
        return rooms;
    }

    public void setRooms(byte rooms) {
        this.rooms = rooms;
    }

    public boolean getFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public short getPrice() {
        return price;
    }

    public void setPrice(short price) {
        this.price = price;
    }

    public byte getLevel() {
        return level;
    }

    public void setLevel(byte level) {
        this.level = level;
    }
}
