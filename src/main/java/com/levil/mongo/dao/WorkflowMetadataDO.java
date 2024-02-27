package com.levil.mongo.dao;


import io.github.code.visual.model.ScriptMetadata;
import io.github.code.visual.model.WorkflowParameters;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "sp_workflow_metadata")
@Data
public class WorkflowMetadataDO extends BaseEntity {

    private Integer workflowId;
    private String workflowName;
    private List<WorkflowParameters> workflowParameters;
    private String workflowPurpose;
    private String remark;
    private Date createTime;
    private Date updateTime;
    private ScriptMetadata scriptMetadata;

}
