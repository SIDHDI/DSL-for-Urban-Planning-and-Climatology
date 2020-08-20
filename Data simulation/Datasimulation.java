/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static java.lang.Math.random;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;



public class Datasimulation {
    public static void main(String [] args)
    {
        Datasimulation ds = new Datasimulation();
        ds.Randomlcz_1();
        ds.Randomlcz_2();
        ds.Randomlcz_3();
        ds.Randomlcz_4();
        ds.Randomlcz_5();
        ds.Randomlcz_6();
        ds.Randomlcz_7();
        ds.Randomlcz_8();
        ds.Randomlcz_9();
        ds.Randomlcz_10();
        ds.Randomlcz_A();
        ds.Randomlcz_B();
        ds.Randomlcz_C();
        ds.Randomlcz_D();
        ds.Randomlcz_E();
        ds.Randomlcz_F();
        ds.Randomlcz_G();
    }
    
    public void Randomlcz_1()
    {
        int target = 10;
        int start = 0;
        int l1,b1,h1,q1,l2,b2,h2,q2,l3,b3,h3,q3,to_area,tree_area,bush_area,lake_area;
        double avg_ht;
        Connection con = null ; 
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dataDB","root","password");
            System.out.println("Connection Established");
            Statement stmt = con.createStatement();
            String sql = "SELECT * from region_details";
            int id_region = 0;
                ResultSet rs    = stmt.executeQuery(sql);
                while(rs.next())
                {   
                 id_region = rs.getInt("regionID");
                    
                }
        
        while(start<target)
        {
            l1 = (int)(30.0*Math.random())+5;
            b1 = (int)((l1-5)*Math.random());
            h1 = (int)(30.0*Math.random())+15;
            q1 = (int)(20.0*Math.random())+5;
            
            l2 = (int)(30.0*Math.random())+5;
            b2 = (int)((l2-5)*Math.random());
            h2 = (int)(30.0*Math.random())+15;
            q2 = (int)(20.0*Math.random())+5;
            
            l3 = (int)(30.0*Math.random())+5;
            b3 = (int)((l3-5)*Math.random());
            h3 = (int)(30.0*Math.random())+15;
            q3 = (int)(20.0*Math.random())+5;
            
            to_area = l1*b1*q1 + l2*b2*q2 + l3*b3*q3;
            avg_ht = (1.0*(h1*q1+h2*q2+h3*q3))/(q1+q2+q3);
            
            tree_area = 300*(int)(9.0*Math.random())+300;
            bush_area = (int)(3125*Math.random());
            lake_area = 0;
            
            if(25000 <= to_area && to_area <= 37500 && avg_ht > 25)
            {
                System.out.println("Sample"+start+":");
                System.out.println("Dimensions " + l1+" x "+b1+" x "+h1+" no. of buildings : "+q1);
                System.out.println("Dimensions " + l2+" x "+b2+" x "+h2+" no. of buildings : "+q2);
                System.out.println("Dimensions " + l3+" x "+b3+" x "+h3+" no. of buildings : "+q3);
                System.out.println("Total area = "+ to_area);
                System.out.println("Avg height = "+avg_ht);
                System.out.println("Tree area = "+ tree_area);
                System.out.println("Bush area = "+ bush_area);
                System.out.println("Lake Area = "+lake_area);
                System.out.println();
                
                String str1 = getCreateString(l1, b1, h1, q1);
                String str2 = getCreateString(l2, b2, h2, q2);
                String str3 = getCreateString(l3, b3, h3, q3);
                str1 += ";"+str2+";"+str3+";";
                sql = "INSERT INTO region_details (regionID, buildings, treesArea, bushesArea, lakeArea) VALUES ( "
                    + (start+id_region + 1) + "," + "'" + str1 + "'," + tree_area + "," + bush_area + "," + lake_area + ")";
                stmt.executeUpdate(sql);
                start++;
            }
            
        }
        System.out.println("10 regions with LCZ 1 have been successufully created!");
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
    }
    
    public void Randomlcz_2()
    {
        int target = 10;
        int start = 0;
        int l1,b1,h1,q1,l2,b2,h2,q2,l3,b3,h3,q3,to_area,tree_area,bush_area,lake_area;
        int id_region = 0;
        double avg_ht;
        Connection con = null ; 
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/datadb","root","");
            System.out.println("Connection Established");
            Statement stmt = con.createStatement();
            String sql = "SELECT * from region_details";
                ResultSet rs    = stmt.executeQuery(sql);
                while(rs.next())
                {   
                    id_region = rs.getInt("regionID");
                    
                }
        
        while(start<target)
        {
            l1 = (int)(30.0*Math.random())+5;
            b1 = (int)((l1-5)*Math.random());
            h1 = (int)(20.0*Math.random())+10;
            q1 = (int)(20.0*Math.random())+5;
            
            l2 = (int)(30.0*Math.random())+5;
            b2 = (int)((l2-5)*Math.random());
            h2 = (int)(20.0*Math.random())+10;
            q2 = (int)(20.0*Math.random())+5;
            
            l3 = (int)(30.0*Math.random())+5;
            b3 = (int)((l3-5)*Math.random());
            h3 = (int)(20.0*Math.random())+10;
            q3 = (int)(20.0*Math.random())+5;
            
            to_area = l1*b1*q1 + l2*b2*q2 + l3*b3*q3;
            avg_ht = (1.0*(h1*q1+h2*q2+h3*q3))/(q1+q2+q3);
            
            tree_area = 300*(int)(9.0*Math.random())+300;
            bush_area = (int)(3125*Math.random());
            lake_area = 0;
            
            if(25000 <= to_area && to_area <= 43750 && avg_ht >= 10 && avg_ht <= 25)
            {
                System.out.println("Sample"+(start+1)+":");
                System.out.println("Dimensions " + l1+" x "+b1+" x "+h1+" no. of buildings : "+q1);
                System.out.println("Dimensions " + l2+" x "+b2+" x "+h2+" no. of buildings : "+q2);
                System.out.println("Dimensions " + l3+" x "+b3+" x "+h3+" no. of buildings : "+q3);
                System.out.println("Total building area = "+ to_area);
                System.out.println("Avg height = "+avg_ht);
                System.out.println("Tree area = "+ tree_area);
                System.out.println("Bush area = "+ bush_area);
                System.out.println("Lake Area = "+lake_area);
                System.out.println();
                String str1 = getCreateString(l1, b1, h1, q1);
                String str2 = getCreateString(l2, b2, h2, q2);
                String str3 = getCreateString(l3, b3, h3, q3);
                str1 += ";"+str2+";"+str3+";";
                sql = "INSERT INTO region_details (regionID, buildings, treesArea, bushesArea, lakeArea) VALUES ( "
                    + (start+id_region+1) + "," + "'" + str1 + "'," + tree_area + "," + bush_area + "," + lake_area + ")";
                stmt.executeUpdate(sql);
                start++;
            }
        }
        System.out.println("10 regions with LCZ 2 have been successufully created!");
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
    }
    
    public void Randomlcz_3()
    {
        int target = 10;
        int start = 0;
        int l1,b1,h1,q1,l2,b2,h2,q2,l3,b3,h3,q3,to_area,tree_area,bush_area,lake_area;
        int id_region = 0;
        double avg_ht;
        Connection con = null ; 
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/datadb","root","");
            System.out.println("Connection Established");
            Statement stmt = con.createStatement();
            String sql = "SELECT * from region_details";
                ResultSet rs    = stmt.executeQuery(sql);
                while(rs.next())
                {   
                    id_region = rs.getInt("regionID");
                    
                }
        
        while(start<target)
        {
            l1 = (int)(10.0*Math.random())+5;
            b1 = (int)((l1-5)*Math.random());
            h1 = (int)(10.0*Math.random())+2;
            q1 = (int)(80.0*Math.random())+30;
            
            l2 = (int)(10.0*Math.random())+5;
            b2 = (int)((l2-5)*Math.random());
            h2 = (int)(10.0*Math.random())+2;
            q2 = (int)(80.0*Math.random())+30;
            
            l3 = (int)(10.0*Math.random())+5;
            b3 = (int)((l3-5)*Math.random());
            h3 = (int)(10.0*Math.random())+2;
            q3 = (int)(80.0*Math.random())+30;
            
            to_area = l1*b1*q1 + l2*b2*q2 + l3*b3*q3;
            avg_ht = (1.0*(h1*q1+h2*q2+h3*q3))/(q1+q2+q3);
            
            tree_area = 300*(int)(9.0*Math.random())+300;
            bush_area = (int)(3125*Math.random());
            lake_area = 0;
            
            if(25000 <= to_area && to_area <= 43750 && avg_ht >= 3 && avg_ht < 10)
            {
                System.out.println("Sample"+start+":");
                System.out.println("Dimensions " + l1+" x "+b1+" x "+h1+" no. of buildings : "+q1);
                System.out.println("Dimensions " + l2+" x "+b2+" x "+h2+" no. of buildings : "+q2);
                System.out.println("Dimensions " + l3+" x "+b3+" x "+h3+" no. of buildings : "+q3);
                System.out.println("Total area = "+ to_area);
                System.out.println("Avg height = "+avg_ht);
                System.out.println("Tree area = "+ tree_area);
                System.out.println("Bush area = "+ bush_area);
                System.out.println("Lake Area = "+lake_area);
                System.out.println();
                
            
                
                
                String str1 = getCreateString(l1, b1, h1, q1);
                String str2 = getCreateString(l2, b2, h2, q2);
                String str3 = getCreateString(l3, b3, h3, q3);
                str1 += ";"+str2+";"+str3+";";
                sql = "INSERT INTO region_details (regionID, buildings, treesArea, bushesArea, lakeArea) VALUES ( "
                    + (start+id_region+1) + "," + "'" + str1 + "'," + tree_area + "," + bush_area + "," + lake_area + ")";
                stmt.executeUpdate(sql);
                start++;
            }
        }
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
    }
        
    public void Randomlcz_4()
    {
        int target = 10;
        int start = 0;
        int l1,b1,h1,q1,l2,b2,h2,q2,l3,b3,h3,q3,to_area,tree_area,bush_area,lake_area;
        int id_region = 0;
        double avg_ht;
        Connection con = null ; 
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/datadb","root","");
            System.out.println("Connection Established");
            Statement stmt = con.createStatement();
            String sql = "SELECT * from region_details";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next())
                {   
                    id_region = rs.getInt("regionID");
                    
                }
        
        while(start<target)
        {
            l1 = (int)(30.0*Math.random())+5;
            b1 = (int)((l1-5)*Math.random());
            h1 = (int)(30.0*Math.random())+15;
            q1 = (int)(15.0*Math.random())+5;
            
            l2 = (int)(30.0*Math.random())+5;
            b2 = (int)((l2-5)*Math.random());
            h2 = (int)(30.0*Math.random())+15;
            q2 = (int)(15.0*Math.random())+5;
            
            l3 = (int)(30.0*Math.random())+5;
            b3 = (int)((l3-5)*Math.random());
            h3 = (int)(30.0*Math.random())+15;
            q3 = (int)(15.0*Math.random())+5;
            
            
            to_area = l1*b1*q1 + l2*b2*q2 + l3*b3*q3;
            avg_ht = (1.0*(h1*q1+h2*q2+h3*q3))/(q1+q2+q3);
            
            tree_area = 300*(int)(10.0*Math.random())+6300;
            bush_area = (int)(3125*Math.random())+6250;
            lake_area = (int)((62500-tree_area-to_area-bush_area)*Math.random());
            
            if(12500 <= to_area && to_area < 25000 && avg_ht > 25)
            {
                System.out.println("Sample"+start+":");
                System.out.println("Dimensions " + l1+" x "+b1+" x "+h1+" no. of buildings : "+q1);
                System.out.println("Dimensions " + l2+" x "+b2+" x "+h2+" no. of buildings : "+q2);
                System.out.println("Dimensions " + l3+" x "+b3+" x "+h3+" no. of buildings : "+q3);
                System.out.println("Total area = "+ to_area);
                System.out.println("Avg height = "+avg_ht);
                System.out.println("Tree area = "+ tree_area);
                System.out.println("Bush area = "+ bush_area);
                System.out.println("Lake Area = "+lake_area);
                System.out.println();
                
            
                
                
                String str1 = getCreateString(l1, b1, h1, q1);
                String str2 = getCreateString(l2, b2, h2, q2);
                String str3 = getCreateString(l3, b3, h3, q3);
                str1 += ";"+str2+";"+str3+";";
                sql = "INSERT INTO region_details (regionID, buildings, treesArea, bushesArea, lakeArea) VALUES ( "
                    + (start+id_region+1) + "," + "'" + str1 + "'," + tree_area + "," + bush_area + "," + lake_area + ")";
                stmt.executeUpdate(sql);
                start++;
            }
        }
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
    }
            
    public void Randomlcz_5()
    {
        int target = 10;
        int start = 0;
        int l1,b1,h1,q1,l2,b2,h2,q2,l3,b3,h3,q3,to_area,tree_area,bush_area,lake_area;
        int id_region = 0;
        double avg_ht;
        Connection con = null ; 
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/datadb","root","");
            System.out.println("Connection Established");
            Statement stmt = con.createStatement();
            String sql = "SELECT * from region_details";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next())
                {   
                    id_region = rs.getInt("regionID");
                    
                }
        
        while(start<target)
        {
            l1 = (int)(30.0*Math.random())+5;
            b1 = (int)((l1-5)*Math.random());
            h1 = (int)(20.0*Math.random())+10;
            q1 = (int)(15.0*Math.random())+5;
            
            l2 = (int)(30.0*Math.random())+5;
            b2 = (int)((l2-5)*Math.random());
            h2 = (int)(20.0*Math.random())+10;
            q2 = (int)(15.0*Math.random())+5;
            
            l3 = (int)(30.0*Math.random())+5;
            b3 = (int)((l3-5)*Math.random());
            h3 = (int)(20.0*Math.random())+10;
            q3 = (int)(15.0*Math.random())+5;
            
            
            to_area = l1*b1*q1 + l2*b2*q2 + l3*b3*q3;
            avg_ht = (1.0*(h1*q1+h2*q2+h3*q3))/(q1+q2+q3);
            
            tree_area = 300*(int)(20.0*Math.random())+3300;
            bush_area = (int)(6250*Math.random())+3125;
            lake_area = (int)((62500-tree_area-to_area-bush_area)*Math.random());
            
            if(12500 <= to_area && to_area < 25000 && avg_ht >10 && avg_ht <= 25)
            {
                System.out.println("Sample"+start+":");
                System.out.println("Dimensions " + l1+" x "+b1+" x "+h1+" no. of buildings : "+q1);
                System.out.println("Dimensions " + l2+" x "+b2+" x "+h2+" no. of buildings : "+q2);
                System.out.println("Dimensions " + l3+" x "+b3+" x "+h3+" no. of buildings : "+q3);
                System.out.println("Total area = "+ to_area);
                System.out.println("Avg height = "+avg_ht);
                System.out.println("Tree area = "+ tree_area);
                System.out.println("Bush area = "+ bush_area);
                System.out.println("Lake Area = "+lake_area);
                System.out.println();
                
            
                
                
                String str1 = getCreateString(l1, b1, h1, q1);
                String str2 = getCreateString(l2, b2, h2, q2);
                String str3 = getCreateString(l3, b3, h3, q3);
                str1 += ";"+str2+";"+str3+";";
                sql = "INSERT INTO region_details (regionID, buildings, treesArea, bushesArea, lakeArea) VALUES ( "
                    + (start+id_region+1) + "," + "'" + str1 + "'," + tree_area + "," + bush_area + "," + lake_area + ")";
                stmt.executeUpdate(sql);
                start++;
            }
        }
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
    }

    
    public void Randomlcz_6()
    {
        int target = 10;
        int start = 0;
        int l1,b1,h1,q1,l2,b2,h2,q2,l3,b3,h3,q3,to_area,tree_area,bush_area,lake_area;
        int id_region = 0;
        double avg_ht;
        Connection con = null; 
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/datadb","root","");
            System.out.println("Connection Established");
            Statement stmt = con.createStatement();
            String sql = "SELECT * from region_details";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next())
                {   
                    id_region = rs.getInt("regionID");
                    
                }
        
        while(start<target)
        {
            l1 = (int)(10.0*Math.random())+5;
            b1 = (int)((l1-5)*Math.random());
            h1 = (int)(10.0*Math.random())+2;
            q1 = (int)(40.0*Math.random())+20;
            
            l2 = (int)(10.0*Math.random())+5;
            b2 = (int)((l2-5)*Math.random());
            h2 = (int)(10.0*Math.random())+2;
            q2 = (int)(40.0*Math.random())+20;
            
            l3 = (int)(10.0*Math.random())+5;
            b3 = (int)((l3-5)*Math.random());
            h3 = (int)(10.0*Math.random())+2;
            q3 = (int)(40.0*Math.random())+20;
            
            
            to_area = l1*b1*q1 + l2*b2*q2 + l3*b3*q3;
            avg_ht = (1.0*(h1*q1+h2*q2+h3*q3))/(q1+q2+q3);
            
            tree_area = 300*(int)(19.0*Math.random())+6300;
            bush_area = (int)(6250*Math.random())+6250;
            lake_area = (int)((62500-tree_area-to_area-bush_area)*Math.random());
            
            if(12500 <= to_area && to_area < 25000 && avg_ht >= 3 && avg_ht < 10)
            {
                System.out.println("Sample"+start+":");
                System.out.println("Dimensions " + l1+" x "+b1+" x "+h1+" no. of buildings : "+q1);
                System.out.println("Dimensions " + l2+" x "+b2+" x "+h2+" no. of buildings : "+q2);
                System.out.println("Dimensions " + l3+" x "+b3+" x "+h3+" no. of buildings : "+q3);
                System.out.println("Total area = "+ to_area);
                System.out.println("Avg height = "+avg_ht);
                System.out.println("Tree area = "+ tree_area);
                System.out.println("Bush area = "+ bush_area);
                System.out.println("Lake Area = "+lake_area);
                System.out.println();
                
            
                
                
                String str1 = getCreateString(l1, b1, h1, q1);
                String str2 = getCreateString(l2, b2, h2, q2);
                String str3 = getCreateString(l3, b3, h3, q3);
                str1 += ";"+str2+";"+str3+";";
                sql = "INSERT INTO region_details (regionID, buildings, treesArea, bushesArea, lakeArea) VALUES ( "
                    + (start+id_region+1) + "," + "'" + str1 + "'," + tree_area + "," + bush_area + "," + lake_area + ")";
                stmt.executeUpdate(sql);
                start++;
            }
        }
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
    }
    
    public void Randomlcz_7()
    {
        int target = 10;
        int start = 0;
        int l1,b1,h1,q1,l2,b2,h2,q2,l3,b3,h3,q3,to_area,tree_area,bush_area,lake_area;
        int id_region = 0;
        double avg_ht;
        Connection con = null; 
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/datadb","root","");
            System.out.println("Connection Established");
            Statement stmt = con.createStatement();
            String sql = "SELECT * from region_details";
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next())
                {   
                    id_region = rs.getInt("regionID");
                    
                }
        
        while(start<target)
        {
            l1 = (int)(10.0*Math.random())+5;
            b1 = (int)((l1-5)*Math.random());
            h1 = (int)(4.0*Math.random())+2;
            q1 = (int)(40.0*Math.random())+100;
            
            l2 = (int)(10.0*Math.random())+5;
            b2 = (int)((l2-5)*Math.random());
            h2 = (int)(4.0*Math.random())+2;
            q2 = (int)(40.0*Math.random())+100;
            
            l3 = (int)(10.0*Math.random())+5;
            b3 = (int)((l3-5)*Math.random());
            h3 = (int)(4.0*Math.random())+2;
            q3 = (int)(40.0*Math.random())+100;
            
            
            to_area = l1*b1*q1 + l2*b2*q2 + l3*b3*q3;
            avg_ht = (1.0*(h1*q1+h2*q2+h3*q3))/(q1+q2+q3);
            
            tree_area = 300*(int)(31.0*Math.random());
            bush_area = (int)(9375*Math.random());
            lake_area = 0;
            
            if(37500 <= to_area && to_area <= 56250 && avg_ht >= 2 && avg_ht <= 4)
            {
                System.out.println("Sample"+start+":");
                System.out.println("Dimensions " + l1+" x "+b1+" x "+h1+" no. of buildings : "+q1);
                System.out.println("Dimensions " + l2+" x "+b2+" x "+h2+" no. of buildings : "+q2);
                System.out.println("Dimensions " + l3+" x "+b3+" x "+h3+" no. of buildings : "+q3);
                System.out.println("Total area = "+ to_area);
                System.out.println("Avg height = "+avg_ht);
                System.out.println("Tree area = "+ tree_area);
                System.out.println("Bush area = "+ bush_area);
                System.out.println("Lake Area = "+lake_area);
                System.out.println();
                
            
                
                
                String str1 = getCreateString(l1, b1, h1, q1);
                String str2 = getCreateString(l2, b2, h2, q2);
                String str3 = getCreateString(l3, b3, h3, q3);
                str1 += ";"+str2+";"+str3+";";
                sql = "INSERT INTO region_details (regionID, buildings, treesArea, bushesArea, lakeArea) VALUES ( "
                    + (start+id_region+1) + "," + "'" + str1 + "'," + tree_area + "," + bush_area + "," + lake_area + ")";
                stmt.executeUpdate(sql);
                start++;
            }
        }
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
    }
    
      public void Randomlcz_8()
    {
        int target = 10;
        int start = 0;
        int l1,b1,h1,q1,l2,b2,h2,q2,l3,b3,h3,q3,to_area,tree_area,bush_area,lake_area;
        int id_region = 0;
        double avg_ht;
        Connection con = null ; 
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/datadb","root","");
            System.out.println("Connection Established");
            Statement stmt = con.createStatement();
            String sql = "SELECT * from region_details";
                ResultSet rs    = stmt.executeQuery(sql);
                while(rs.next())
                {   
                    id_region = rs.getInt("regionID");
                    
                }
        
        while(start<target)
        {
            l1 = (int)(10.0*Math.random())+5;
            b1 = (int)((l1-5)*Math.random());
            h1 = (int)(10.0*Math.random())+2;
            q1 = (int)(80.0*Math.random())+50;
            
            l2 = (int)(10.0*Math.random())+5;
            b2 = (int)((l2-5)*Math.random());
            h2 = (int)(10.0*Math.random())+2;
            q2 = (int)(80.0*Math.random())+50;
            
            l3 = (int)(10.0*Math.random())+5;
            b3 = (int)((l3-5)*Math.random());
            h3 = (int)(10.0*Math.random())+2;
            q3 = (int)(80.0*Math.random())+50;
            
            to_area = l1*b1*q1 + l2*b2*q2 + l3*b3*q3;
            avg_ht = (1.0*(h1*q1+h2*q2+h3*q3))/(q1+q2+q3);
            
            tree_area = 300*(int)(9.0*Math.random())+3300;
            bush_area = (int)(3125*Math.random())+3125;
            lake_area = 0;
            
            if(18750 <= to_area && to_area <= 31250 && avg_ht >= 3 && avg_ht < 10)
            {
                System.out.println("Sample"+start+":");
                System.out.println("Dimensions " + l1+" x "+b1+" x "+h1+" no. of buildings : "+q1);
                System.out.println("Dimensions " + l2+" x "+b2+" x "+h2+" no. of buildings : "+q2);
                System.out.println("Dimensions " + l3+" x "+b3+" x "+h3+" no. of buildings : "+q3);
                System.out.println("Total area = "+ to_area);
                System.out.println("Avg height = "+avg_ht);
                System.out.println("Tree area = "+ tree_area);
                System.out.println("Bush area = "+ bush_area);
                System.out.println("Lake Area = "+lake_area);
                System.out.println();
                
            
                
                
                String str1 = getCreateString(l1, b1, h1, q1);
                String str2 = getCreateString(l2, b2, h2, q2);
                String str3 = getCreateString(l3, b3, h3, q3);
                str1 += ";"+str2+";"+str3+";";
                sql = "INSERT INTO region_details (regionID, buildings, treesArea, bushesArea, lakeArea) VALUES ( "
                    + (start+id_region+1) + "," + "'" + str1 + "'," + tree_area + "," + bush_area + "," + lake_area + ")";
                stmt.executeUpdate(sql);
                start++;
            }
        }
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
    }
      
    public void Randomlcz_9()
    {
        int target = 10;
        int start = 0;
        int l1,b1,h1,q1,l2,b2,h2,q2,l3,b3,h3,q3,to_area,tree_area,bush_area,lake_area;
        int id_region = 0;
        double avg_ht;
        Connection con = null ; 
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/datadb","root","");
            System.out.println("Connection Established");
            Statement stmt = con.createStatement();
            String sql = "SELECT * from region_details";
                ResultSet rs    = stmt.executeQuery(sql);
                while(rs.next())
                {   
                    id_region = rs.getInt("regionID");
                    
                }
        
        while(start<target)
        {
            l1 = (int)(10.0*Math.random())+5;
            b1 = (int)((l1-5)*Math.random());
            h1 = (int)(10.0*Math.random())+2;
            q1 = (int)(40.0*Math.random())+20;
            
            l2 = (int)(10.0*Math.random())+5;
            b2 = (int)((l2-5)*Math.random());
            h2 = (int)(10.0*Math.random())+2;
            q2 = (int)(40.0*Math.random())+20;
            
            l3 = (int)(10.0*Math.random())+5;
            b3 = (int)((l3-5)*Math.random());
            h3 = (int)(10.0*Math.random())+2;
            q3 = (int)(40.0*Math.random())+20;
            
            to_area = l1*b1*q1 + l2*b2*q2 + l3*b3*q3;
            avg_ht = (1.0*(h1*q1+h2*q2+h3*q3))/(q1+q2+q3);
            
            tree_area = 300*(int)(19.0*Math.random())+6300;
            bush_area = (int)(6250*Math.random())+6250;
            lake_area = 0;
            
            if(6250 <= to_area && to_area < 12500 && avg_ht >= 3 && avg_ht < 10)
            {
                System.out.println("Sample"+start+":");
                System.out.println("Dimensions " + l1+" x "+b1+" x "+h1+" no. of buildings : "+q1);
                System.out.println("Dimensions " + l2+" x "+b2+" x "+h2+" no. of buildings : "+q2);
                System.out.println("Dimensions " + l3+" x "+b3+" x "+h3+" no. of buildings : "+q3);
                System.out.println("Total area = "+ to_area);
                System.out.println("Avg height = "+avg_ht);
                System.out.println("Tree area = "+ tree_area);
                System.out.println("Bush area = "+ bush_area);
                System.out.println("Lake Area = "+lake_area);
                System.out.println();
                
            
                
                
                String str1 = getCreateString(l1, b1, h1, q1);
                String str2 = getCreateString(l2, b2, h2, q2);
                String str3 = getCreateString(l3, b3, h3, q3);
                str1 += ";"+str2+";"+str3+";";
                sql = "INSERT INTO region_details (regionID, buildings, treesArea, bushesArea, lakeArea) VALUES ( "
                    + (start+id_region+1) + "," + "'" + str1 + "'," + tree_area + "," + bush_area + "," + lake_area + ")";
                stmt.executeUpdate(sql);
                start++;
            }
        }
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
    }  
    
    public void Randomlcz_10()
    {
        int target = 10;
        int start = 0;
        int l1,b1,h1,q1,l2,b2,h2,q2,l3,b3,h3,q3,to_area,tree_area,bush_area,lake_area;
        int id_region = 0;
        double avg_ht;
        Connection con = null ; 
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/datadb","root","");
            System.out.println("Connection Established");
            Statement stmt = con.createStatement();
            String sql = "SELECT * from region_details";
                ResultSet rs    = stmt.executeQuery(sql);
                while(rs.next())
                {   
                    id_region = rs.getInt("regionID");
                    
                }
        
        while(start<target)
        {
            l1 = (int)(30.0*Math.random())+5;
            b1 = (int)((l1-5)*Math.random());
            h1 = (int)(20.0*Math.random())+5;
            q1 = (int)(20.0*Math.random())+5;
            
            l2 = (int)(30.0*Math.random())+5;
            b2 = (int)((l2-5)*Math.random());
            h2 = (int)(20.0*Math.random())+5;
            q2 = (int)(20.0*Math.random())+5;
            
            l3 = (int)(30.0*Math.random())+5;
            b3 = (int)((l3-5)*Math.random());
            h3 = (int)(20.0*Math.random())+5;
            q3 = (int)(20.0*Math.random())+5;
            
            to_area = l1*b1*q1 + l2*b2*q2 + l3*b3*q3;
            avg_ht = (1.0*(h1*q1+h2*q2+h3*q3))/(q1+q2+q3);
            
            tree_area = 300*(int)(9.0*Math.random())+300;
            bush_area = (int)(3125*Math.random());
            lake_area = 0;
            
            if(12500 <= to_area && to_area <= 18750 && avg_ht >= 5 && avg_ht < 15)
            {
                System.out.println("Sample"+start+":");
                System.out.println("Dimensions " + l1+" x "+b1+" x "+h1+" no. of buildings : "+q1);
                System.out.println("Dimensions " + l2+" x "+b2+" x "+h2+" no. of buildings : "+q2);
                System.out.println("Dimensions " + l3+" x "+b3+" x "+h3+" no. of buildings : "+q3);
                System.out.println("Total area = "+ to_area);
                System.out.println("Avg height = "+avg_ht);
                System.out.println("Tree area = "+ tree_area);
                System.out.println("Bush area = "+ bush_area);
                System.out.println("Lake Area = "+lake_area);
                System.out.println();
                
            
                
                
                String str1 = getCreateString(l1, b1, h1, q1);
                String str2 = getCreateString(l2, b2, h2, q2);
                String str3 = getCreateString(l3, b3, h3, q3);
                str1 += ";"+str2+";"+str3+";";
                sql = "INSERT INTO region_details (regionID, buildings, treesArea, bushesArea, lakeArea) VALUES ( "
                    + (start+id_region+1) + "," + "'" + str1 + "'," + tree_area + "," + bush_area + "," + lake_area + ")";
                stmt.executeUpdate(sql);
                start++;
            }
        }
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
    }
    public void Randomlcz_A()
    {
        int target = 10;
        int start = 0;
        int l1,b1,h1,q1,l2,b2,h2,q2,l3,b3,h3,q3,to_area,tree_area,bush_area,lake_area;
        int id_region = 0;
        double avg_ht;
        Connection con = null ; 
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/datadb","root","");
            System.out.println("Connection Established");
            Statement stmt = con.createStatement();
            String sql = "SELECT * from region_details";
                ResultSet rs    = stmt.executeQuery(sql);
                while(rs.next())
                {   
                    id_region = rs.getInt("regionID");
                    
                }
        
        while(start<target)
        {
            l1 = (int)(10.0*Math.random())+5;
            b1 = (int)((l1-5)*Math.random());
            h1 = (int)(5.0*Math.random())+2;
            q1 = (int)(10.0*Math.random())+20;
            
            l2 = (int)(10.0*Math.random())+5;
            b2 = (int)((l2-5)*Math.random());
            h2 = (int)(5.0*Math.random())+2;
            q2 = (int)(10.0*Math.random())+20;
            
            l3 = (int)(10.0*Math.random())+5;
            b3 = (int)((l3-5)*Math.random());
            h3 = (int)(5.0*Math.random())+2;
            q3 = (int)(10.0*Math.random())+20;
            
            to_area = l1*b1*q1 + l2*b2*q2 + l3*b3*q3;
            avg_ht = (1.0*(h1*q1+h2*q2+h3*q3))/(q1+q2+q3);
            
            tree_area = 300*(int)(62.0*Math.random())+43750;
            bush_area = (int)(18750*Math.random());
            lake_area = (int)(18750*Math.random());
            
            if(to_area < 6250  && (to_area+tree_area+bush_area+lake_area) <= 62500)
            {
                System.out.println("Sample"+start+":");
                System.out.println("Dimensions " + l1+" x "+b1+" x "+h1+" no. of buildings : "+q1);
                System.out.println("Dimensions " + l2+" x "+b2+" x "+h2+" no. of buildings : "+q2);
                System.out.println("Dimensions " + l3+" x "+b3+" x "+h3+" no. of buildings : "+q3);
                System.out.println("Total area = "+ to_area);
                System.out.println("Avg height = "+avg_ht);
                System.out.println("Tree area = "+ tree_area);
                System.out.println("Bush area = "+ bush_area);
                System.out.println("Lake Area = "+lake_area);
                System.out.println();
                
            
                
                
                String str1 = getCreateString(l1, b1, h1, q1);
                String str2 = getCreateString(l2, b2, h2, q2);
                String str3 = getCreateString(l3, b3, h3, q3);
                str1 += ";"+str2+";"+str3+";";
                sql = "INSERT INTO region_details (regionID, buildings, treesArea, bushesArea, lakeArea) VALUES ( "
                    + (start+id_region+1) + "," + "'" + str1 + "'," + tree_area + "," + bush_area + "," + lake_area + ")";
                stmt.executeUpdate(sql);
                start++;
            }
        }
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
    }  
    public void Randomlcz_B()
    {
        int target = 10;
        int start = 0;
        int l1,b1,h1,q1,l2,b2,h2,q2,l3,b3,h3,q3,to_area,tree_area,bush_area,lake_area;
        int id_region = 0;
        double avg_ht;
        Connection con = null ; 
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/datadb","root","");
            System.out.println("Connection Established");
            Statement stmt = con.createStatement();
            String sql = "SELECT * from region_details";
                ResultSet rs    = stmt.executeQuery(sql);
                while(rs.next())
                {   
                    id_region = rs.getInt("regionID");
                    
                }
        
        while(start<target)
        {
            l1 = (int)(10.0*Math.random())+5;
            b1 = (int)((l1-5)*Math.random());
            h1 = (int)(5.0*Math.random())+2;
            q1 = (int)(10.0*Math.random())+20;
            
            l2 = (int)(10.0*Math.random())+5;
            b2 = (int)((l2-5)*Math.random());
            h2 = (int)(5.0*Math.random())+2;
            q2 = (int)(10.0*Math.random())+20;
            
            l3 = (int)(10.0*Math.random())+5;
            b3 = (int)((l3-5)*Math.random());
            h3 = (int)(5.0*Math.random())+2;
            q3 = (int)(10.0*Math.random())+20;
            
            to_area = l1*b1*q1 + l2*b2*q2 + l3*b3*q3;
            avg_ht = (1.0*(h1*q1+h2*q2+h3*q3))/(q1+q2+q3);
            
            tree_area = 300*(int)(82.0*Math.random())+18750;
            bush_area = (int)(18750*Math.random())+25000;
            lake_area = (int)(25000*Math.random());
            
              if(to_area < 6250  && (to_area+tree_area+bush_area+lake_area) <= 62500)
            {
                System.out.println("Sample"+start+":");
                System.out.println("Dimensions " + l1+" x "+b1+" x "+h1+" no. of buildings : "+q1);
                System.out.println("Dimensions " + l2+" x "+b2+" x "+h2+" no. of buildings : "+q2);
                System.out.println("Dimensions " + l3+" x "+b3+" x "+h3+" no. of buildings : "+q3);
                System.out.println("Total area = "+ to_area);
                System.out.println("Avg height = "+avg_ht);
                System.out.println("Tree area = "+ tree_area);
                System.out.println("Bush area = "+ bush_area);
                System.out.println("Lake Area = "+lake_area);
                System.out.println();
                
            
                
                
                String str1 = getCreateString(l1, b1, h1, q1);
                String str2 = getCreateString(l2, b2, h2, q2);
                String str3 = getCreateString(l3, b3, h3, q3);
                str1 += ";"+str2+";"+str3+";";
                sql = "INSERT INTO region_details (regionID, buildings, treesArea, bushesArea, lakeArea) VALUES ( "
                    + (start+id_region+1) + "," + "'" + str1 + "'," + tree_area + "," + bush_area + "," + lake_area + ")";
                stmt.executeUpdate(sql);
                start++;
            }
        }
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
    }  
    public void Randomlcz_C()
    {
        int target = 10;
        int start = 0;
        int l1,b1,h1,q1,l2,b2,h2,q2,l3,b3,h3,q3,to_area,tree_area,bush_area,lake_area;
        int id_region = 0;
        double avg_ht;
        Connection con = null ; 
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/datadb","root","");
            System.out.println("Connection Established");
            Statement stmt = con.createStatement();
            String sql = "SELECT * from region_details";
                ResultSet rs    = stmt.executeQuery(sql);
                while(rs.next())
                {   
                    id_region = rs.getInt("regionID");
                    
                }
        
        while(start<target)
        {
            l1 = (int)(10.0*Math.random())+5;
            b1 = (int)((l1-5)*Math.random());
            h1 = (int)(5.0*Math.random())+2;
            q1 = (int)(10.0*Math.random())+20;
            
            l2 = (int)(10.0*Math.random())+5;
            b2 = (int)((l2-5)*Math.random());
            h2 = (int)(5.0*Math.random())+2;
            q2 = (int)(10.0*Math.random())+20;
            
            l3 = (int)(10.0*Math.random())+5;
            b3 = (int)((l3-5)*Math.random());
            h3 = (int)(5.0*Math.random())+2;
            q3 = (int)(10.0*Math.random())+20;
            
            to_area = l1*b1*q1 + l2*b2*q2 + l3*b3*q3;
            avg_ht = (1.0*(h1*q1+h2*q2+h3*q3))/(q1+q2+q3);
            
            tree_area = 300*(int)(10.0*Math.random());
            bush_area = (int)(43750*Math.random())+12500;
            lake_area = (int)(18750*Math.random());
            
            if(to_area < 6250  && (to_area+tree_area+bush_area+lake_area) <= 62500)
            {
                System.out.println("Sample"+start+":");
                System.out.println("Dimensions " + l1+" x "+b1+" x "+h1+" no. of buildings : "+q1);
                System.out.println("Dimensions " + l2+" x "+b2+" x "+h2+" no. of buildings : "+q2);
                System.out.println("Dimensions " + l3+" x "+b3+" x "+h3+" no. of buildings : "+q3);
                System.out.println("Total area = "+ to_area);
                System.out.println("Avg height = "+avg_ht);
                System.out.println("Tree area = "+ tree_area);
                System.out.println("Bush area = "+ bush_area);
                System.out.println("Lake Area = "+lake_area);
                System.out.println();
                
            
                
                
                String str1 = getCreateString(l1, b1, h1, q1);
                String str2 = getCreateString(l2, b2, h2, q2);
                String str3 = getCreateString(l3, b3, h3, q3);
                str1 += ";"+str2+";"+str3+";";
                sql = "INSERT INTO region_details (regionID, buildings, treesArea, bushesArea, lakeArea) VALUES ( "
                    + (start+id_region+1) + "," + "'" + str1 + "'," + tree_area + "," + bush_area + "," + lake_area + ")";
                stmt.executeUpdate(sql);
                start++;
            }
        }
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
    }  
    public void Randomlcz_D()
    {
        int target = 10;
        int start = 0;
        int l1,b1,h1,q1,l2,b2,h2,q2,l3,b3,h3,q3,to_area,tree_area,bush_area,lake_area;
        int id_region = 0;
        double avg_ht;
        Connection con = null ; 
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/datadb","root","");
            System.out.println("Connection Established");
            Statement stmt = con.createStatement();
            String sql = "SELECT * from region_details";
                ResultSet rs    = stmt.executeQuery(sql);
                while(rs.next())
                {   
                    id_region = rs.getInt("regionID");
                    
                }
        
        while(start<target)
        {
            l1 = (int)(10.0*Math.random())+5;
            b1 = (int)((l1-5)*Math.random());
            h1 = (int)(5.0*Math.random())+2;
            q1 = (int)(10.0*Math.random())+20;
            
            l2 = (int)(10.0*Math.random())+5;
            b2 = (int)((l2-5)*Math.random());
            h2 = (int)(5.0*Math.random())+2;
            q2 = (int)(10.0*Math.random())+20;
            
            l3 = (int)(10.0*Math.random())+5;
            b3 = (int)((l3-5)*Math.random());
            h3 = (int)(5.0*Math.random())+2;
            q3 = (int)(10.0*Math.random())+20;
            
            to_area = l1*b1*q1 + l2*b2*q2 + l3*b3*q3;
            avg_ht = (1.0*(h1*q1+h2*q2+h3*q3))/(q1+q2+q3);
            
            tree_area = 300*(int)(19.0*Math.random())+6300;
            bush_area = (int)(43750*Math.random())+18750;
            lake_area = (int)(43750*Math.random());
            
            if(to_area < 6250  && (to_area+tree_area+bush_area+lake_area) <= 62500)
            {
                System.out.println("Sample"+start+":");
                System.out.println("Dimensions " + l1+" x "+b1+" x "+h1+" no. of buildings : "+q1);
                System.out.println("Dimensions " + l2+" x "+b2+" x "+h2+" no. of buildings : "+q2);
                System.out.println("Dimensions " + l3+" x "+b3+" x "+h3+" no. of buildings : "+q3);
                System.out.println("Total area = "+ to_area);
                System.out.println("Avg height = "+avg_ht);
                System.out.println("Tree area = "+ tree_area);
                System.out.println("Bush area = "+ bush_area);
                System.out.println("Lake Area = "+lake_area);
                System.out.println();
                
            
                
                
                String str1 = getCreateString(l1, b1, h1, q1);
                String str2 = getCreateString(l2, b2, h2, q2);
                String str3 = getCreateString(l3, b3, h3, q3);
                str1 += ";"+str2+";"+str3+";";
                sql = "INSERT INTO region_details (regionID, buildings, treesArea, bushesArea, lakeArea) VALUES ( "
                    + (start+id_region+1) + "," + "'" + str1 + "'," + tree_area + "," + bush_area + "," + lake_area + ")";
                stmt.executeUpdate(sql);
                start++;
            }
        }
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
    }  
    public void Randomlcz_E()
    {
        int target = 10;
        int start = 0;
        int l1,b1,h1,q1,l2,b2,h2,q2,l3,b3,h3,q3,to_area,tree_area,bush_area,lake_area;
        int id_region = 0;
        double avg_ht;
        Connection con = null ; 
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/datadb","root","");
            System.out.println("Connection Established");
            Statement stmt = con.createStatement();
            String sql = "SELECT * from region_details";
                ResultSet rs    = stmt.executeQuery(sql);
                while(rs.next())
                {   
                    id_region = rs.getInt("regionID");
                    
                }
        
        while(start<target)
        {
            l1 = (int)(10.0*Math.random())+5;
            b1 = (int)((l1-5)*Math.random());
            h1 = (int)(5.0*Math.random())+2;
            q1 = (int)(10.0*Math.random())+20;
            
            l2 = (int)(10.0*Math.random())+5;
            b2 = (int)((l2-5)*Math.random());
            h2 = (int)(5.0*Math.random())+2;
            q2 = (int)(10.0*Math.random())+20;
            
            l3 = (int)(10.0*Math.random())+5;
            b3 = (int)((l3-5)*Math.random());
            h3 = (int)(5.0*Math.random())+2;
            q3 = (int)(10.0*Math.random())+20;
            
            to_area = l1*b1*q1 + l2*b2*q2 + l3*b3*q3;
            avg_ht = (1.0*(h1*q1+h2*q2+h3*q3))/(q1+q2+q3);
            
            tree_area = 300*(int)(10.0*Math.random());
            bush_area = (int)(3125*Math.random());
            lake_area = 0;
            
            if(to_area < 6250  && (to_area+tree_area+bush_area+lake_area) <= 62500)
            {
                System.out.println("Sample"+start+":");
                System.out.println("Dimensions " + l1+" x "+b1+" x "+h1+" no. of buildings : "+q1);
                System.out.println("Dimensions " + l2+" x "+b2+" x "+h2+" no. of buildings : "+q2);
                System.out.println("Dimensions " + l3+" x "+b3+" x "+h3+" no. of buildings : "+q3);
                System.out.println("Total area = "+ to_area);
                System.out.println("Avg height = "+avg_ht);
                System.out.println("Tree area = "+ tree_area);
                System.out.println("Bush area = "+ bush_area);
                System.out.println("Lake Area = "+lake_area);
                System.out.println();
                
            
                
                
                String str1 = getCreateString(l1, b1, h1, q1);
                String str2 = getCreateString(l2, b2, h2, q2);
                String str3 = getCreateString(l3, b3, h3, q3);
                str1 += ";"+str2+";"+str3+";";
                sql = "INSERT INTO region_details (regionID, buildings, treesArea, bushesArea, lakeArea) VALUES ( "
                    + (start+id_region+1) + "," + "'" + str1 + "'," + tree_area + "," + bush_area + "," + lake_area + ")";
                stmt.executeUpdate(sql);
                start++;
            }
        }
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
    }  
    public void Randomlcz_F()
    {
        int target = 10;
        int start = 0;
        int l1,b1,h1,q1,l2,b2,h2,q2,l3,b3,h3,q3,to_area,tree_area,bush_area,lake_area;
        int id_region = 0;
        double avg_ht;
        Connection con = null ; 
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/datadb","root","");
            System.out.println("Connection Established");
            Statement stmt = con.createStatement();
            String sql = "SELECT * from region_details";
                ResultSet rs    = stmt.executeQuery(sql);
                while(rs.next())
                {   
                    id_region = rs.getInt("regionID");
                    
                }
        
        while(start<target)
        {
            l1 = (int)(10.0*Math.random())+5;
            b1 = (int)((l1-5)*Math.random());
            h1 = (int)(5.0*Math.random())+2;
            q1 = (int)(10.0*Math.random())+20;
            
            l2 = (int)(10.0*Math.random())+5;
            b2 = (int)((l2-5)*Math.random());
            h2 = (int)(5.0*Math.random())+2;
            q2 = (int)(10.0*Math.random())+20;
            
            l3 = (int)(10.0*Math.random())+5;
            b3 = (int)((l3-5)*Math.random());
            h3 = (int)(5.0*Math.random())+2;
            q3 = (int)(10.0*Math.random())+20;
            
            to_area = l1*b1*q1 + l2*b2*q2 + l3*b3*q3;
            avg_ht = (1.0*(h1*q1+h2*q2+h3*q3))/(q1+q2+q3);
            
            tree_area = 300*(int)(10.0*Math.random());
            bush_area = (int)(3125*Math.random());
            lake_area = 0;
            
            if(to_area < 6250  && (to_area+tree_area+bush_area+lake_area) <= 62500)
            {
                System.out.println("Sample"+start+":");
                System.out.println("Dimensions " + l1+" x "+b1+" x "+h1+" no. of buildings : "+q1);
                System.out.println("Dimensions " + l2+" x "+b2+" x "+h2+" no. of buildings : "+q2);
                System.out.println("Dimensions " + l3+" x "+b3+" x "+h3+" no. of buildings : "+q3);
                System.out.println("Total area = "+ to_area);
                System.out.println("Avg height = "+avg_ht);
                System.out.println("Tree area = "+ tree_area);
                System.out.println("Bush area = "+ bush_area);
                System.out.println("Lake Area = "+lake_area);
                System.out.println();
                
            
                
                
                String str1 = getCreateString(l1, b1, h1, q1);
                String str2 = getCreateString(l2, b2, h2, q2);
                String str3 = getCreateString(l3, b3, h3, q3);
                str1 += ";"+str2+";"+str3+";";
                sql = "INSERT INTO region_details (regionID, buildings, treesArea, bushesArea, lakeArea) VALUES ( "
                    + (start+id_region+1) + "," + "'" + str1 + "'," + tree_area + "," + bush_area + "," + lake_area + ")";
                stmt.executeUpdate(sql);
                start++;
            }
        }
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
    }  
    public void Randomlcz_G()
    {
        int target = 10;
        int start = 0;
        int l1,b1,h1,q1,l2,b2,h2,q2,l3,b3,h3,q3,to_area,tree_area,bush_area,lake_area;
        int id_region = 0;
        double avg_ht;
        Connection con = null ; 
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/datadb","root","");
            System.out.println("Connection Established");
            Statement stmt = con.createStatement();
            String sql = "SELECT * from region_details";
                ResultSet rs    = stmt.executeQuery(sql);
                while(rs.next())
                {   
                    id_region = rs.getInt("regionID");
                    
                }
        
        while(start<target)
        {
            l1 = (int)(10.0*Math.random())+5;
            b1 = (int)((l1-5)*Math.random());
            h1 = (int)(5.0*Math.random())+2;
            q1 = (int)(10.0*Math.random())+20;
            
            l2 = (int)(10.0*Math.random())+5;
            b2 = (int)((l2-5)*Math.random());
            h2 = (int)(5.0*Math.random())+2;
            q2 = (int)(10.0*Math.random())+20;
            
            l3 = (int)(10.0*Math.random())+5;
            b3 = (int)((l3-5)*Math.random());
            h3 = (int)(5.0*Math.random())+2;
            q3 = (int)(10.0*Math.random())+20;
            
            to_area = l1*b1*q1 + l2*b2*q2 + l3*b3*q3;
            avg_ht = (1.0*(h1*q1+h2*q2+h3*q3))/(q1+q2+q3);
            
            tree_area = 300*(int)(10.0*Math.random());
            bush_area = (int)(3125*Math.random());
            lake_area = (int)(56250*Math.random());
            
            if(to_area < 6250  && (to_area+tree_area+bush_area+lake_area) <= 62500)
            {
                System.out.println("Sample"+start+":");
                System.out.println("Dimensions " + l1+" x "+b1+" x "+h1+" no. of buildings : "+q1);
                System.out.println("Dimensions " + l2+" x "+b2+" x "+h2+" no. of buildings : "+q2);
                System.out.println("Dimensions " + l3+" x "+b3+" x "+h3+" no. of buildings : "+q3);
                System.out.println("Total area = "+ to_area);
                System.out.println("Avg height = "+avg_ht);
                System.out.println("Tree area = "+ tree_area);
                System.out.println("Bush area = "+ bush_area);
                System.out.println("Lake Area = "+lake_area);
                System.out.println();
                
            
                
                
                String str1 = getCreateString(l1, b1, h1, q1);
                String str2 = getCreateString(l2, b2, h2, q2);
                String str3 = getCreateString(l3, b3, h3, q3);
                str1 += ";"+str2+";"+str3+";";
                sql = "INSERT INTO region_details (regionID, buildings, treesArea, bushesArea, lakeArea) VALUES ( "
                    + (start+id_region+1) + "," + "'" + str1 + "'," + tree_area + "," + bush_area + "," + lake_area + ")";
                stmt.executeUpdate(sql);
                start++;
            }
        }
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
    }   
    private static String getCreateString(int l, int b, int h, int qnt) {
        String str = "";
        for(int i = 0; i< qnt-1; i++)
        {
            str += Integer.toString(l) + "," +  Integer.toString(b) + "," +  Integer.toString(h) + ";" ;
        }
        str += Integer.toString(l) + "," +  Integer.toString(b) + "," +  Integer.toString(h) ;
        return str;
    }
    
    
    
}
