package com.levil.mongo.api;

import com.github.managetech.model.ScriptMetadata;
import com.github.managetech.model.WorkflowMetadata;
import com.github.managetech.workflow.WorkflowManagerImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@Slf4j
public class api {


    @GetMapping(value = "/api2")
    public Object sendSingleEmail() {

        WorkflowManagerImpl bean = com.github.managetech.utils.SpringContext.getBean(com.github.managetech.workflow.WorkflowManagerImpl.class);
        WorkflowMetadata workflowMetadata = new com.github.managetech.model.WorkflowMetadata();
        workflowMetadata.setRemark("");
        workflowMetadata.setWorkflowName("groovy Test Workflow");
        workflowMetadata.setWorkflowDescription("");
        workflowMetadata.setCreateTime(new Date());
        workflowMetadata.setUpdateTime(new Date());
        workflowMetadata.setScriptMetadata(new ScriptMetadata());

        bean.createWorkflow(workflowMetadata);
        return UUID.randomUUID().toString();
    }
}
