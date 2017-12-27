
package com.ismaiel.easysheet;


public interface GC
{
    public String reportBalanceQuery="SELECT SUM( share.amount ) AS A, member.id AS B, member.name AS C\n" +
"FROM SHARE , MEMBER, SHEET\n" +
"WHERE share.member_id = member.id\n" +
"AND sheet.id = member.sheet_id\n" +
"AND sheet.id = ?1  \n" +
"GROUP BY member.id ";
    
    
    public String reportTotalQuery="SELECT SUM( entry.amount ) AS A\n" +
"FROM ENTRY, SHEET\n" +
"WHERE sheet.id = entry.sheet_id\n" +
"AND sheet.id =?1 \n" +
"GROUP BY sheet.id";
}
