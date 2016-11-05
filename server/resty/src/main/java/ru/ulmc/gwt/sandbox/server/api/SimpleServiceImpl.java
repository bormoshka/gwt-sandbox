package ru.ulmc.gwt.sandbox.server.api;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.ulmc.gwt.sandbox.shared.api.SimpleService;
import ru.ulmc.gwt.sandbox.shared.model.CityModel;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(SimpleService.CONTROLLER_URL)
public class SimpleServiceImpl implements SimpleService {
    Logger logger = LoggerFactory.getLogger(SimpleServiceImpl.class);
    Gson gson = new Gson();
   static List<CityModel> citiesList = new ArrayList<>();
    static {

        citiesList.add(new CityModel("Шанхай", "КНР", 24150000L, 6340.5F));
        citiesList.add(new CityModel("Карачи", "Пакистан", 23500000L, 3527.00F));
        citiesList.add(new CityModel("Пекин", "КНР", 21516000L, 16410.54f));
        citiesList.add(new CityModel("Дели", "Индия", 16314838L, 1484.00F));
        citiesList.add(new CityModel("Лагос", "Нигерия", 15118780L, 999.58F));
        citiesList.add(new CityModel("Стамбул", "Турция", 13854740L, 5461.00F));
        citiesList.add(new CityModel("Гуанчжоу", "КНР", 13080500L, 3843.43F));
        citiesList.add(new CityModel("Мумбаи", "Индия", 12478447L, 603.4F));
        citiesList.add(new CityModel("Токио", "Япония", 13370198L, 622.99F));
        citiesList.add(new CityModel("Москва", "Россия", 12197596L, 2561.5F));
        citiesList.add(new CityModel("Дакка", "Бангладеш", 12043977L, 815.8F));
        citiesList.add(new CityModel("Каир", "Египет", 11922949L, 3085.1F));
        citiesList.add(new CityModel("Сан-Паулу", "Бразилия", 11895893L, 1521.11F));
        citiesList.add(new CityModel("Лахор", "Пакистан", 11318745L, 1772.00F));
        citiesList.add(new CityModel("Шэньчжэнь", "КНР", 10467400L, 1991.64F));
        citiesList.add(new CityModel("Сеул", "Республика Корея", 10388055L, 605.21F));
        citiesList.add(new CityModel("Джакарта", "Индонезия", 9988329L, 664.12F));
        citiesList.add(new CityModel("Киншаса", "Дем. Респ. Конго", 9735000L, 1117.62F));
        citiesList.add(new CityModel("Тяньцзинь", "КНР", 9341844L, 4037.00F));
        citiesList.add(new CityModel("Мехико", "Мексика", 8874724L, 1485.49F));
    }

    @Override
    @RequestMapping(path = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public String hello() {
        return gson.toJson("Hello!");
    }

    @Override
    @RequestMapping(path = "/wait", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public String waitForMe() throws Exception {
        Thread.sleep(5000);
        return gson.toJson("wait!!");
    }

    @Override
    @RequestMapping(path = "/getCities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public List<CityModel> getCities() throws Exception {
        return citiesList;
    }

    @Override
    @GetMapping(path = "/stepOne", produces = MediaType.APPLICATION_JSON)
    public String stepOne() throws Exception {
        Thread.sleep(500);
        return gson.toJson("stepOneCompleted!");
    }

    @Override
    @GetMapping(path = "/stepTwo/{input}/{throwError}", produces = MediaType.APPLICATION_JSON)
    public Long stepTwo(@PathVariable boolean throwError, @PathVariable String input) throws Exception {
        logger.debug("stepTwo {}", input);
        if (throwError) {
            throw new Exception("Ouch!");
        }
        Thread.sleep(500);
        return 1337L;
    }

    @Override
    @GetMapping(path = "/stepThree/{input}",produces = MediaType.APPLICATION_JSON)
    public List<String> stepThree(@PathVariable Long input) throws Exception {
        logger.debug("stepThree {}", input);
        Thread.sleep(1000);
        List<String> list = new ArrayList<>();
        list.add("some");
        list.add("strings");
        return list;
    }
}
