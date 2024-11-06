package com.example.propertypro.Pojo;

public class ProvincePOJO {
    private int province_id;
    private String province;

    public ProvincePOJO(int province_id, String province){
        this.province_id=province_id;
        this.province=province;
    }

    public int getProvince_id() {
        return province_id;
    }

    public void setProvince_id(int province_id) {
        this.province_id = province_id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
    @Override
    public String toString() {
        return province;
    }
}
