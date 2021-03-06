import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
import static java.lang.System.out;
import java.lang.*;

import java.util.Map;
import java.util.HashMap;

import spark.ModelAndView;

import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App{
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

   get("/patients/new/specialties", (request, response) -> {
     HashMap<String, Object> model = new HashMap<String, Object>();
     model.put("template", "templates/specialties.vtl");
     Specialty spec1 = new Specialty("broken heart");
     spec1.save();
     Specialty spec2 = new Specialty("nose, ear, throat");
     spec2.save();
     Specialty spec3 = new Specialty("liver");
     spec3.save();
     model.put("specialties", Specialty.all());


     return new ModelAndView(model, layout);
   }, new VelocityTemplateEngine());


  }
}
