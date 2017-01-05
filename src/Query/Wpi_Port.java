package Query;

/**
 * Created by qcrao.
 * Email: qcrao@foxmail.com
 * Date: 17/1/4
 * Time: 16:34
 */
public class Wpi_Port {
    int id;
    int index, region_index;
    String name;
    String wpi_country_code;
    int longitude, latitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getRegion_index() {
        return region_index;
    }

    public void setRegion_index(int region_index) {
        this.region_index = region_index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWpi_country_code() {
        return wpi_country_code;
    }

    public void setWpi_country_code(String wpi_country_code) {
        this.wpi_country_code = wpi_country_code;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Wpi_Port{" +
                "id=" + id +
                ", index=" + index +
                ", region_index=" + region_index +
                ", name='" + name + '\'' +
                ", wpi_country_code='" + wpi_country_code + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
