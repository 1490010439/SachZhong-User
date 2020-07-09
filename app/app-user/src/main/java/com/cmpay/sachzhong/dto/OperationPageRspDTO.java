package com.cmpay.sachzhong.dto;

import com.cmpay.sachzhong.entity.OperationDO;
import java.util.List;

public class OperationPageRspDTO extends PageRspDTO{


    private List<OperationDO> operations;

    public List<OperationDO> getOperations() {
        return operations;
    }

    public void setOperations(List<OperationDO> operations) {
        this.operations = operations;
    }
}
