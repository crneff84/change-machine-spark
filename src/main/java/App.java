import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/change", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String stringUserInput = request.queryParams("dollars");
      Float floatUserInput = Float.parseFloat(stringUserInput);
      ChangeMachine newChange = new ChangeMachine();
      model.put("result", newChange.makeChange(floatUserInput));
      model.put("template", "templates/results.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
