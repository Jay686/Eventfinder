package rpc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class RecommendItem
 */
@WebServlet(name = "recommendation", urlPatterns = { "/recommendation" }) //name of the endPoint
public class RecommendItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecommendItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JSONArray array = new JSONArray();
		try {
			//new JSONObject worked as HashMap, sequence of key value pair id not important
			array.put(new JSONObject().put("username", "lily").put("address", "San Francisco").put("time", "01/01/2020"));
			array.put(new JSONObject().put("username", "shikii").put("address", "San Jose").put("time", "01/02/2020"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		RpcHelper.writeJsonArray(response, array);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
