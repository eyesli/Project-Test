package com.levil.mongo.dao;

import io.github.code.visual.model.WorkflowIdAndName;
import io.github.code.visual.model.WorkflowMetadata;
import io.github.code.visual.workflow.WorkflowMetadataRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class workflowdao implements WorkflowMetadataRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public workflowdao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void create(WorkflowMetadata workflowMetadata) {
        WorkflowMetadataDO workflowMetadataDO = new WorkflowMetadataDO();
        BeanUtils.copyProperties(workflowMetadata, workflowMetadataDO);
        workflowMetadataDO.setWorkflowId(Math.toIntExact(System.currentTimeMillis() / 1000));
        mongoTemplate.save(workflowMetadataDO);
    }

    @Override
    public Object deleteByWorkflowId(Integer workflowName) {
        return null;
    }

    @Override
    public WorkflowMetadata findByWorkflowId(Integer workflowId) {
        Query queryWorkflowMetadata = new Query();
        queryWorkflowMetadata.addCriteria(Criteria.where("workflowId").is(workflowId));
        WorkflowMetadataDO one = this.mongoTemplate.findOne(queryWorkflowMetadata, WorkflowMetadataDO.class);
        WorkflowMetadata workflowMetadata = new WorkflowMetadata();
        assert one != null;
        BeanUtils.copyProperties(one, workflowMetadata);
        return workflowMetadata;

    }

    @Override
    public List<WorkflowIdAndName> getMenuWorkflowList() {
        List<WorkflowMetadataDO> all = this.mongoTemplate.findAll(WorkflowMetadataDO.class);
        return all.stream().map(e -> new WorkflowIdAndName(e.getWorkflowId(), e.getWorkflowName())).collect(Collectors.toList());
    }

    @Override
    public WorkflowMetadata updateWorkflowMetadata(WorkflowMetadata workflowMetadata) {

        Query queryWorkflowMetadata = new Query();
        queryWorkflowMetadata.addCriteria(Criteria.where("workflowId").is(workflowMetadata.getWorkflowId()));
        WorkflowMetadataDO metadataDO = this.mongoTemplate.findOne(queryWorkflowMetadata, WorkflowMetadataDO.class);

        if (metadataDO == null) {

            throw new IllegalArgumentException("WorkflowMetadata with id " + workflowMetadata.getWorkflowId() + " does not exist");
        }

        if (workflowMetadata.getWorkflowName() != null) {
            metadataDO.setWorkflowName(workflowMetadata.getWorkflowName());
        }
        if (workflowMetadata.getWorkflowParameters() != null) {
            metadataDO.setWorkflowParameters(workflowMetadata.getWorkflowParameters());
        }
        if (workflowMetadata.getWorkflowPurpose() != null) {
            metadataDO.setWorkflowPurpose(workflowMetadata.getWorkflowPurpose());
        }
        if (workflowMetadata.getRemark() != null) {
            metadataDO.setRemark(workflowMetadata.getRemark());
        }
        if (workflowMetadata.getScriptMetadata() != null) {
            metadataDO.setScriptMetadata(workflowMetadata.getScriptMetadata());
        }

        WorkflowMetadataDO save = this.mongoTemplate.save(metadataDO);

        WorkflowMetadata workflowMetadata1 = new WorkflowMetadata();
        BeanUtils.copyProperties(save, workflowMetadata1);
        return workflowMetadata1;
    }

    @Override
    public WorkflowMetadata findByWorkflowName(String workflowName) {
        return null;
    }
}
