package com.levil.mongo.groovy

import com.github.managetech.utils.SpringContext
import com.levil.mongo.api.Api
def api22 = SpringContext.getBean(Api.class).api22()
binding.setVariable("api22", api22)
String currentDir = System.getProperty("user.dir")
return currentDir