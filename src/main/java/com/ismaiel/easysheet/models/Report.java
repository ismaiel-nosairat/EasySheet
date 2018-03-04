
package com.ismaiel.easysheet.models;

import com.ismaiel.easysheet.entities.Member;
import com.ismaiel.easysheet.entities.Sheet;
import java.util.ArrayList;
import java.util.List;


public class Report
{
    public long id;
    public List<String> members;
    public List<Double> balances;
    public Double total;
    public boolean isThereData;

    public Report() {
        members=new ArrayList<>();
        balances=new ArrayList<>();
    }

    public Report(long id, List<String> members, List<Double> balances, Double total,boolean isThereData) {
        this.id = id;
        this.members = members;
        this.balances = balances;
        this.total = total;
        this.isThereData=isThereData;
    }
    
    
}
