/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Lokesh Chandra
 */
public class MakeOnline {
    
    MakeOnline()
    {
        try{
            String sql = "update info set status = '1' where userid = '"+Login.uid+"'";
            MySql msql = new MySql();
            PreparedStatement pstmt = msql.conn.prepareStatement(sql);
            int i = pstmt.executeUpdate();
            
            if(i>0){
                System.out.println("Successfully online ....!!!!");
            }
            else
                System.out.println("Error to be online ....!!!!");
        }
        catch(SQLException e){
        e.printStackTrace();}
    }
}
