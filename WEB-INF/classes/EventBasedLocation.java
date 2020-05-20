import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EventBasedLocation")

/* 
	Home class uses the printHtml Function of Utilities class and prints the Header,LeftNavigationBar,
	Content,Footer of Game Speed Application.

*/

public class EventBasedLocation extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        HashMap<String,Event> hm=new HashMap<String,Event>();
		//StringBuffer sb = request.getRequestURL();
		
		//String value = getQueryMap(sb.toString());
		String value = request.getParameter("locationLink");
		System.out.println(value);
		
		
		try{
            hm=MySqlDataStoreUtilities.getEventBasedLocation(value);
        } catch(Exception e){
            System.out.print(e);
        }
		Utilities utility = new Utilities(request,pw);
        utility.printHtml("Header.html");
        pw.print("<section class='ftco-section ftco-no-pt ftco-no-pb ftco-counter img' id='section-counter' style='margin-top:5rem;background-image: url(images/bg_1.jpg);'>");
        pw.print("<div class='container' style='padding-top: 3em;'><div class='row no-gutters slider-text align-items-end justify-content-center'><div class='col-md-9 ftco-animate pb-5 text-center fadeInUp ftco-animated'>");
        pw.print("<h1 class='mb-3 bread'>List of Games</h1>");
        pw.print("<p class='breadcrumbs'><span class='mr-2'><a href='Home'>Home <i class='ion-ios-arrow-forward'></i></a></span> <span>Games<i class='ion-ios-arrow-forward'></i></span></p></div></div></div>");
        pw.print("<div class='container' style='background:white;'><div class='row d-flex'><div class='heading-section ftco-animate fadeInUp ftco-animated p-5'>");
        pw.print("<h2 class='mb-4' style='margin-left:100px;'>See What Games are being Played Around You</h2><div class='GameList' style='height:55rem;overflow-y:scroll;'><div class='row d-flex'> ");
        // for(int i=0;i<=20;i++){
            int i = 0;
        for(Map.Entry<String, Event> entry : hm.entrySet()){
            Event g = entry.getValue();
            if(i%4==0 && i>4){
                pw.print("</div><div class='row d-flex'>");
            }
            pw.print("<div class='col-lg-3 p-sm-3 '><div id='game_"+entry.getKey()+"' class='scoreboard'>");
            pw.print("<div class='divider text-center'><h4> "+g.getEventName()+"</h4><span>"+g.getSubGenre()+" <span> <span>"+g.getGenre()+"</span> <br/> <span>"+g.getEventDate()+" </span>  <span>"+g.getEventTime()+"</span></div>");
            pw.print("<div class='d-sm-flex mb-4 sport-team'><img src='"+g.getImageUrl()+"' class='logo' style='margin: 10% 50%;'/></div> "); 
            pw.print("<div class='text-center'><p><form method='post' action='Cart'>");
            pw.print("<input type='hidden' name='name' value='"+g.getEventName()+"'><input type='hidden' name='id' value='"+entry.getKey()+"'>");
            pw.print("<input type='submit' class='btn btn-primary' value='Buy Tickets'></form></p>");
            
            pw.print("<p><form method='post' action='WriteReview'>"+
					"<input type='hidden' name='id' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='name' value='"+g.getEventName()+"'>"+
					"<input type='hidden' name='type' value='"+g.getGenre()+"'>"+
					"<input type='hidden' name='maker' value='"+g.getSubGenre()+"'>"+
					"<input type='hidden' name='price' value='"+g.getMaxPrice()+"'>"+
					"<input type='hidden' name='access' value=''>"+
                    "<input type='submit' value='WriteReview' class='btn btn-primary'></form></p>");
                    
            // pw.print("<input type='submit' class='btn btn-primary' value='Write Review'></form></p></div>");
             pw.print("<p><form method='post' action='ViewReview'>"+
                    "<input type='hidden' name='id' value='"+entry.getKey()+"'>"+
                    "<input type='hidden' name='name' value='"+g.getEventName()+"'>"+
					"<input type='hidden' name='type' value='"+g.getGenre()+"'>"+
					"<input type='hidden' name='maker' value='"+g.getSubGenre()+"'>"+
					"<input type='hidden' name='access' value=''>"+
                    "<input type='submit' value='ViewReview' class='btn btn-primary'></form></p>");

            if (utility.usertype() != null){
                if(utility.usertype().equals("manager")){
                        pw.print("<div class='row'> <div class='col-md-5'><form method='get' action='EventModify'>"+
                        "<input type='hidden' name='id' value='"+entry.getKey()+"'>"+
                        "<input type='hidden' name='button' value='Updateevent'>"+
                        "<input type='submit' value='Edit Event' class='btn btn-primary'></form></div>");
                        pw.print(" <div class='col-md-5'><form method='get' action='EventModify'>"+
                        "<input type='hidden' name='id' value='"+entry.getKey()+"'>"+
                        "<input type='hidden' name='button' value='Deleteevent'>"+
                        "<input type='submit' value='Delete Event' class='btn btn-primary'></form></div></div>");
                    }       
                }             
            pw.print("</div></div></div>");
            i = i+1;
        } 
        
	    pw.print("</div></div></div></div></section>");     
				
		//utility.printHtml("About.html");
		utility.printHtml("Footer.html");
				
	}
	
	
	/*public static String getQueryMap(String query)  
{  
    String[] params = query.split("\\?");
    String[] val = params[1].split("=");
    String value = val[1]; 
    
	return value;  
}*/

}
