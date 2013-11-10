package controllers;

import play.*;
import play.mvc.*;
import java.util.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
    	String s = new String("Your new application is ready.");
    	Integer n = new Integer(1000);

        return ok(index.render(s));
    }

}
