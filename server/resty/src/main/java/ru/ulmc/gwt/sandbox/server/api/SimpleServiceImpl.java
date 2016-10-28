package ru.ulmc.gwt.sandbox.server.api;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ulmc.gwt.sandbox.shared.api.SimpleService;
import ru.ulmc.gwt.sandbox.shared.model.CityModel;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(SimpleService.CONTROLLER_IMPL_URL)
public class SimpleServiceImpl implements SimpleService {
    Logger logger = LoggerFactory.getLogger(SimpleServiceImpl.class);
    Gson gson = new Gson();

    @Override
    @RequestMapping(path = "/hello", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN)
    @ResponseBody
    public String hello() {
        return gson.toJson("Hello!");
    }

    @Override
    @RequestMapping(path = "/wait", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN)
    @ResponseBody
    public String waitForMe() throws Exception {
        Thread.sleep(5000);
        return gson.toJson("wait!!");
    }
    @Override
    @RequestMapping(path = "/getCities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    public List<CityModel> getCities() throws Exception {
        List<CityModel> list = new ArrayList<>();
        list.add(new CityModel("Шанхай",	"КНР",		        24150000L,		6340.5F));
        list.add(new CityModel("Карачи",	"Пакистан",		    23500000L,		3527.00F));
        list.add(new CityModel("Пекин",		"КНР",			    21516000L,		16410.54f));
        list.add(new CityModel("Дели",		"Индия",		    16314838L,		1484.00F));
        list.add(new CityModel("Лагос",		"Нигерия",		    15118780L,		999.58F));
        list.add(new CityModel("Стамбул",	"Турция",		    13854740L,		5461.00F));
        list.add(new CityModel("Гуанчжоу",	"КНР",		        13080500L,		3843.43F));
        list.add(new CityModel("Мумбаи",	"Индия",		    12478447L,		603.4F));
        list.add(new CityModel("Токио",		"Япония",		    13370198L,		622.99F));
        list.add(new CityModel("Москва",	"Россия",		    12197596L,		2561.5F));
        list.add(new CityModel("Дакка",		"Бангладеш",	    12043977L,		815.8F));
        list.add(new CityModel("Каир",		"Египет",		    11922949L,		3085.1F));
        list.add(new CityModel("Сан-Паулу",	"Бразилия",		    11895893L,		1521.11F));
        list.add(new CityModel("Лахор",		"Пакистан",		    11318745L,		1772.00F));
        list.add(new CityModel("Шэньчжэнь",	"КНР",			    10467400L,		1991.64F));
        list.add(new CityModel("Сеул",		"Республика Корея",	10388055L,		605.21F));
        list.add(new CityModel("Джакарта",	"Индонезия",		9988329L,		664.12F));
        list.add(new CityModel("Киншаса",	"Дем. Респ. Конго",	9735000L,		1117.62F));
        list.add(new CityModel("Тяньцзинь",	"КНР",			    9341844L,		4037.00F));
        list.add(new CityModel("Мехико",	"Мексика",			8874724L,		1485.49F));
       // list.add(new CityModel("",				"",			0L,		0F));
        return list;
    }


}
