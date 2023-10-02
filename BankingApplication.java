package bankDemo1;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
class account extends BankDemo1
{
    static int min_bal=1000;
    int bal;
    void withdraw(int req){
        if(req%100==0){
        if(bal>req ){
        bal=bal-req;
        if(req%500==0){
        int five=(req-500)/500;
        int hud=(req-five*500)/100;
        System.out.println("collect your cash \n 500 notes = "+five+"\n 100 notes = "+hud);

        }
        else{
            int five=req/500;
            int hud=(req-five*500)/100;
        System.out.println("collect your cash \n 500 notes = "+five+"\n 100 notes = "+hud);

        }
        }
        else{
        System.out.println("Insufficient Balance");

        }}
        else{
        System.out.println("Try again by entering amount in multiples of 100");

        }
        if(bal<1000)
        {
            System.out.println("you don't have minimum balance.");

        }
     }
     void deposit(int req){
        bal=bal+req;
        System.out.println("Successfully Deposited");
        if(bal<1000)
        {
            System.out.println("you don't have minimum balance.");

        }
     }
     void balance(){
        System.out.println("your Balance amount is"+bal);
        if(bal<1000)
        {
            System.out.println("you don't have minimum balance.");

        }
         
     }
     

}

public class BankDemo1 {

	public   static void main(String args[])throws Exception
	{
		Scanner sc=new Scanner(System.in);
		int pin2=0;
Class.forName("com.mysql.cj.jdbc.Driver");
System.out.println("Driver class load");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","Kollapavan.03");
PreparedStatement st4=con.prepareStatement("insert into account values(?,?,?,?)");
PreparedStatement st=con.prepareStatement("select * from account where accno=?");
PreparedStatement st3=con.prepareStatement("update account set balance=? where accno=?");
PreparedStatement st1=con.prepareStatement("insert into transc(accno,deposit) values(?,?)");
PreparedStatement st2=con.prepareStatement("insert into transc(accno,withdraw) values(?,?)");
System.out.println("Enter your accno details(accid,cusname,balance,pin)");
System.out.println("Enter your accno details(accid,cusname,balance,pin)");
int accid=sc.nextInt();
String name=sc.next();
int balance=sc.nextInt();
int pin1=sc.nextInt();
st4.setInt(1, accid);
st4.setString(1, name);
st4.setInt(1, balance);
st4.setInt(1, pin1);
st4.execute();
System.out.println("Details inserted Sucessfully");
System.out.println("Enter your accno");
int no=sc.nextInt();
st.setInt(1, no);
ResultSet rs=st.executeQuery();
account ac=new account();
System.out.println("Enter your pin");
int pin=sc.nextInt();

if(rs.next()) {
ac.bal=rs.getInt(4);
pin2=rs.getInt(5);
}

int repeat=0;
do{
    
    if(pin==pin2){
System.out.println("choose the option");
System.out.println("1.Insert Data");
System.out.println("1.Withdraw");
System.out.println("2.deposit");
System.out.println("3.balance");
System.out.println("4.Exit");

int choice=sc.nextInt();
switch(choice)
{
    case 1:{
        System.out.println("Enter amount that you are willing to withdraw in multiples of 100");
        int req=sc.nextInt();
        st2.setInt(1, no);
        st2.setInt(2, req);
        st2.execute();
        ac.withdraw(req);
        st3.setInt(1, ac.bal);
        st3.setInt(2,no);
        st3.execute();
       
        break;
        
            }
    case 2:
    {
        System.out.println("Enter amount that you are willing to deposit");
        int req=sc.nextInt(); 
        st1.setInt(1, no);
        st1.setInt(2, req);
        st1.execute();
        ac.deposit(req);
        st3.setInt(1, ac.bal);
        st3.setInt(2,no);
        st3.execute();
        
        break;
    }
    case 3:
    {
        ac.balance();
        break;
    }
    case 4:
    {
        break;
    }
}
if(choice!=4){
System.out.println("Do you want to Continue.Enter"+"\n"+"1.YES"+"\n"+"2.NO");
repeat=sc.nextInt();
}
}
   else
   {
      System.out.println("Invalide pin");
      System.out.println("Do you want to Continue.Enter"+"\n"+"1.YES"+"\n"+"2.NO");
      repeat=sc.nextInt();
      if(repeat==1){
      System.out.println("Enter your pin");
       pin=sc.nextInt();
      }
      
   } 
}while(repeat==1);


}

}
