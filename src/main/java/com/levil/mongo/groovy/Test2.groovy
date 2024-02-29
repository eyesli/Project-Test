
import com.levil.mongo.api.Api
import io.github.code.visual.utils.SpringContext

def api22 = SpringContext.getBean(Api.class).api22()
binding.setVariable("api22", api22)
String currentDir = System.getProperty("user.dir")
return currentDir