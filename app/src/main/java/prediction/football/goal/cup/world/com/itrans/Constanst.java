package prediction.football.goal.cup.world.com.itrans;

public class Constanst {
    public static String BASEURL = "http://192.168.43.152/iTrans/itransapi";
    public static String LOGINURL = BASEURL+"/Welcome/auth";
    public static String REGISTERURL = BASEURL + "/Welcome/createUser/";
    public static String CUSTOMERURL = BASEURL+"itrans/";
    public static String CUSTOMERDETAILS=BASEURL+"/Welcome/userDetails/";
    public static String CHECKCUSTOMERDETAILS=BASEURL+"/Welcome/user/";

    public static  String BUSES=BASEURL+"/Welcome/currentBuses";
    public static  String BUSDEFAULT=BASEURL+"/Welcome/getBusDefault";


    // station urls
    public static  String STATIONA=BASEURL+"/Welcome/getStationA";
    public static String STATIONB=BASEURL+"/Welcome/getStationB";
    public static String TIMES=BASEURL+"/Welcome/getTime";
    public static String BOOKURL=BASEURL+"/Welcome/bookBus";
    public static String CANCELURL=BASEURL+"/Welcome/cancelBook/";
    public static String HISTORYBOOKING=BASEURL+"/Welcome/historyBooking";

    //balance
    public static String BALANCE=BASEURL+"/Welcome/currentBalance/";



}