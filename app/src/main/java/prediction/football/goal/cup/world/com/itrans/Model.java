package prediction.football.goal.cup.world.com.itrans;

public class Model {
    String startionA;
    String startionB;
    String time;
    String snumber;
    String plateno;

    public String getAllocationid() {
        return allocationid;
    }

    public void setAllocationid(String allocationid) {
        this.allocationid = allocationid;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    String allocationid;
    int fare;

    public String getPlateno() {
        return plateno;
    }

    public void setPlateno(String plateno) {
        this.plateno = plateno;
    }

    public String getStartionA() {
        return startionA;
    }

    public void setStartionA(String startionA) {
        this.startionA = startionA;
    }

    public String getStartionB() {
        return startionB;
    }

    public void setStartionB(String startionB) {
        this.startionB = startionB;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSnumber() {
        return snumber;
    }

    public void setSnumber(String snumber) {
        this.snumber = snumber;
    }
}
