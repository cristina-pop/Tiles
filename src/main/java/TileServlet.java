import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

@WebServlet("/tiles")
public class TileServlet extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("Intrare in servlet");

        String readLength = request.getParameter("length");
        double length = Double.parseDouble(readLength);

        String readWidth = request.getParameter("width");
        double width = Double.parseDouble(readWidth);

        String model = request.getParameter("model");

        double area = Math.ceil(length * width);
        System.out.println("Area is: " + area + " sqm");

        double price = DbAccessTiles.getPrice(model);
        System.out.println("Price per sqm is: " + price + " EUR/sqm");

        double cost = area * DbAccessTiles.getPrice(model);
        DecimalFormat form = new DecimalFormat("#.##");
        //  cost = Double.valueOf(form.format(cost));
        cost = Double.parseDouble(form.format(cost));

        System.out.println("Total cost is: " + cost + " EUR");

        JSONObject json = new JSONObject();
        json.put("costjson", cost);
        String result = json.toString();
        returnJsonResponse(response, result);
    }

    private void returnJsonResponse(HttpServletResponse response, String jsonResponse) {
        response.setContentType("application/json");
        PrintWriter pr = null;
        try {
            pr = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert pr != null;
        pr.write(jsonResponse);
        pr.close();
    }
}
