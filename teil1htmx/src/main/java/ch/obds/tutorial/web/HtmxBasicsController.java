package ch.obds.tutorial.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

@RestController
public class HtmxBasicsController {

    @GetMapping("/clicked")
    @ResponseBody
    public String clicked() {
        return """
                <p>Guten Tag!</p>
                <p>Das ist ein Server Side Rendered Text, der im <b>Browser</b> angezeigt wird.</p>
                <p>Und das hier ist übrigens ein zufälliger Wert, damit sich auch etwas ändert: <i>%s</i>. Toll, oder? ;-)</p>
                """.formatted(Math.random());
    }

}

