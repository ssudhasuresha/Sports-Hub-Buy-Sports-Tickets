import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.io.*;
import java.sql.*;

@WebServlet("/salesgraph")

public class Salesgraph extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		Utilities utility = new Utilities(request, pw);
		try
         {  
           response.setContentType("text/html");
			if(!utility.isLoggedin())
			{
				HttpSession session = request.getSession(true);				
				session.setAttribute("login_msg", "Please Login to add items to cart");
				response.sendRedirect("Login");
				return;
			}
			HttpSession session=request.getSession(); 	
			utility.printHtml("Header.html");
			// utility.printHtml("LeftNavigationBar.html");

			// pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			// pw.print("<a style='font-size: 24px;'>Sales Graph for all Products</a>");
			// pw.print("</h2><div class='entry' style='overflow: auto;height: 64em;'>");
			// pw.print("<table class='gridtable' >");
			// pw.println("<section id=\"content\">");
			pw.print("<section class='ftco-section ftco-no-pt ftco-no-pb ftco-counter img' id='section-counter' style='margin-top:5rem;background-image: url(images/bg_1.jpg);'>");
			pw.print("<div class='container' style='padding-top: 3em;'><div class='row no-gutters slider-text align-items-end justify-content-center'><div class='col-md-9 ftco-animate pb-5 text-center fadeInUp ftco-animated'>");
			pw.print("<h1 class='mb-3 bread'>List of Games</h1>");
			pw.print("<p class='breadcrumbs'><span class='mr-2'><a href='Home'>Home <i class='ion-ios-arrow-forward'></i></a></span> <span>Account<i class='ion-ios-arrow-forward'></i></span></p></div></div></div>");
			pw.print("<div class='container' style='background:white;'><div class='row d-flex'><div class='heading-section ftco-animate fadeInUp ftco-animated p-5'>");
            pw.println("<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>");
                    pw.println("<script type=\"text/javascript\">");
                    pw.println("google.charts.load('current', {'packages':['bar']});");
                    pw.println("google.charts.setOnLoadCallback(drawStuff);");
                    pw.println("function drawStuff() {");
                    pw.println("var data = new google.visualization.arrayToDataTable([");
                    pw.println("['ProductName', 'Sales'],");

			HashMap<String,List<String>> hm=new HashMap<String,List<String>>();
			double sales = 0.0;
			hm = MySqlDataStoreUtilities.getSaleCount();
			for(Map.Entry<String,List<String>> entry : hm.entrySet()){
				List<String> a = entry.getValue();
				pw.println("[\""+entry.getKey()+"\","+a.get(2)+"],");
				
			}
			pw.println("]);"); 
                                                                                                        
			pw.println("var options = { ");
			pw.println("width: 600,");
			pw.println("chart: {");
			pw.println("title: ' ',");
			pw.println("},");
			pw.println("bars: 'horizontal',");
			pw.println("series: {");
			pw.println("0: { axis: 'distance' }, ");
			pw.println("//1: { axis: 'brightness' } ");
			pw.println("},");
			pw.println("axes: {");
			pw.println("x: { ");
			pw.println("distance: {label: 'No. of Items'},");
			pw.println("} ");
			pw.println("} ");
			pw.println("};");
			pw.println("var chart = new google.charts.Bar(document.getElementById('dual_x_div'));");
			pw.println("chart.draw(data, options);");
			pw.println("};");
			pw.println("</script>");
			pw.println("<div id=\"dual_x_div\" style=\"height:60em;margin-left: 4em;\"></div>");
			pw.println("</section>");  
			pw.print("</table>");

			pw.print("</div></div></div></div></section>"); 
			utility.printHtml("Footer.html");
			
		} catch(Exception e){

		}

	}
	
	
}