package xdesign.georgi.espc;

import com.strongloop.android.loopback.Model;

import java.math.BigInteger;

/**
 * Created by georgi on 13/06/16.
 */
public class User_ESPC extends Model {
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User_ESPC{" +
                "name='" + name + '\'' +
                '}';
    }
}
