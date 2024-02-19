package com.levil.mongo.groovy

import com.github.managetech.utils.SpringContext
import com.levil.mongo.api.Api
def api22 = SpringContext.getBean(Api.class).api22()
return api22