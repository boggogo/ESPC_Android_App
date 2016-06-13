package xdesign.georgi.espc;

import com.strongloop.android.loopback.Model;

/**
 * Created by georgi on 13/06/16.
 */
public class Feature extends Model {

    private String name;
    private int roomID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    @Override
    public String toString() {
        return "Feature{" +
                "name='" + name + '\'' +
                ", roomID=" + roomID +
                '}';
    }
}
