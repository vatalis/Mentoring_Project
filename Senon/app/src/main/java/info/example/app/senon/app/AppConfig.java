package info.example.app.senon.app;

/**
 * Created by AAfif on 5/11/2016.
 */

// In this class we declare the login and registration urls.
// While testing you need to replace the ip address with your localhost pc ip
// IP_nya ganti dengan Ip internet yang kamu pakai

public class AppConfig {
    // Server user login url
    public static String URL_LOGIN = "http://192.168.216.1/android_api/login.php";
    public static String URL_JSPN = "http://192.168.216.1/android_api/getJson.php";


    // Server user register url
    public static String URL_REGISTER = "http://192.168.216.1/android_api/register.php";

}