package com.example.pcproject.models.DTO.ip;

import java.util.List;

public class GetAllIpDTO {

    private List<GetIp> ipList;

    public GetAllIpDTO() {
    }

    public List<GetIp> getIpList() {
        return ipList;
    }

    public void setIpList(List<GetIp> ipList) {
        this.ipList = ipList;
    }
}
