import java.util.*;
import java.io.*;
interface New{
    void withdraw(int with);
    void deposit(int depo);
    void transfer(Customer ob[],int index,int index1,int amount);
    void History(Customer ob[],int index);
    void print(Customer ob[],int index);
    void show_balance();
}


class Customer implements New {
    Scanner scan=new Scanner(System.in); 
    String name,password;
    long balance;
    int last;
            public int start=3;
    String transactionHistory[]=new String[100];
    Customer(){
        name="";
        password="";
        balance=0;
        last=0;
    }
    public void withdraw(int with){
        if(with<=this.balance){
            this.balance=this.balance-with;
        }
        else{
            System.out.println("\nERROR - You don't have Sufficient Balance.\n");
            return;
        }
        transactionHistory[this.last]= with+" is Withdrawn.";
        this.last++;
    }
    public void deposit(int depo){
        this.balance=this.balance+depo;
        transactionHistory[this.last]= depo+" is Deposited.";
        this.last++;
    }
    public void transfer(Customer ob[],int index,int index1,int amount){
        if(amount<=ob[index].balance){
            ob[index].balance=ob[index].balance-amount;
        }
        else{
            System.out.println("\nERROR - You don't have Sufficient Balance.\n");
            return;
        }
        ob[index1].balance=ob[index1].balance+amount;
        transactionHistory[this.last]=amount+" is Transferred to "+ob[index1].name+".";
        ob[index1].transactionHistory[ob[index1].last]=amount+" is Transferred by "+ob[index].name+".";
        this.last++;
        ob[index1].last++;
    }
    public void History(Customer ob[],int index){
        System.out.println("\n--------------------------------------------");
        System.out.println("S.no. \t STATEMENT");
        System.out.println("--------------------------------------------");
        for(int i=0;i<this.last;i++){
            System.out.println((i+1)+"\t Rs."+ob[index].transactionHistory[i]);
        }
        System.out.println("--------------------------------------------\n");
    }
    public void show_balance(){
        System.out.println("\n------------------------------------");
        System.out.println("Your account Balance is : Rs."+this.balance);
        System.out.println("------------------------------------\n");
    }
    public void print(Customer ob[],int x)
    {
         try {
                FileWriter myWriter = new FileWriter(ob[x].name+".txt");
                myWriter.write("------------------------------------------------\n");
                myWriter.write("     Account Details \n");
                
                
                myWriter.write("Account Holder Name :\t\t"+ob[x].name+"\n");
                myWriter.write("Account Balance :\t\t\t"+ob[x].balance+"\n\n");
                 myWriter.write("Transaction History \n\n");
                 
                 
                 for(int i=0;i<this.last;i++){
                     myWriter.write((i+1)+"\t Rs."+ob[x].transactionHistory[i]+"\n");
                 }
                
                myWriter.write("------------------------------------------------\n");
                                
                myWriter.close();
                            // System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            }                       
    }
    
}

class Admin extends Customer{
            // public int start=3;
    void deleteAcc(Customer ob[],String delete){
        String temp1,temp2;
        long temp3;
        for(int i=0;i<ob.length;i++){
            if(ob[i].name.equals(delete)){
                for(int j=i;j<ob.length-1;j++){
                    temp1=ob[j].name;
                    ob[j].name=ob[j+1].name;
                    ob[j+1].name=temp1;

                    temp2=ob[j].password;
                    ob[j].password=ob[j+1].password;
                    ob[j+1].password=temp2;

                    temp3=ob[j].balance;
                    ob[j].balance=ob[j+1].balance;
                    ob[j+1].balance=temp3;
                }
                break;                
            }
        }
        ob[0].start--;
        ob[ob.length-1].name=null;
        ob[ob.length-1].password=null;
        ob[ob.length-1].balance=0;
    }
}

class Main{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Admin obj=new Admin();
        Customer ob[]=new Customer[20];
        for (int i = 0 ; i != ob.length ; i++) {
            ob[i] = new Customer();
        }
        ob[0].name="avi";
        ob[0].password="AT";
        ob[0].balance=1000;

        ob[1].name="aditya";
        ob[1].password="AP";
        ob[1].balance=1000;

        ob[2].name="kunjan";
        ob[2].password="KG";
        ob[2].balance=1000;
        // public int start=3;
        int i,k;
        System.out.println("---------------BANK MANAGEMENT SYSTEM---------------");
        
        outer :for(; ; )
        {
            System.out.println("\n1. Create new Account");
            System.out.println("2. Login as Customer");
            System.out.println("3. Login as Admin");
            System.out.println("4. Exit");
            System.out.print("Enter choice : ");
            int c=sc.nextInt();
            if(c==1)
            {
                System.out.print("\nEnter Username : ");
                String n1,p1;
                n1=sc.next();
                
                boolean flag=true;
                for(int l=0;l<ob[0].start;l++)
                {
                    if(ob[l].name.equals(n1))
                    {
                        flag=false;
                        break;
                    }
                }
                if(flag)
                {
                    System.out.print("Enter Password : ");
                    p1=sc.next();
                    ob[ob[0].start]=new Customer();
                    ob[ob[0].start].name=n1;
                    ob[ob[0].start].password=p1;
                    ob[0].start++;
                   System.out.println("\nYour Account is created successfully. Login to see your Account.\n");
                }
                else
                System.out.println("Username already exists");
            }
            else if(c==2)
            {
               String n,p;
               int index=-1;
               System.out.print("\nEnter Username : ");
               n=sc.next();
               System.out.print("Enter Password : ");
               p=sc.next();
                 for(i=0;i<ob[0].start;i++)
                 {
                     
                   if(ob[i].name.equals(n)&&ob[i].password.equals(p))
                   {
                     index=i;
                     break;
                   }
                 }
                if(index!=-1)
                {
                    inner :for(; ;)
                    {
                        System.out.println("----------------------------------------------------");
                        System.out.println("What do you prefer?\n");
                        System.out.println("1. Display Account Balance");
                        System.out.println("2. Withdraw Money");
                        System.out.println("3. Deposit Money");
                        System.out.println("4. Transfer Money");
                        System.out.println("5. View History of Transactions");
                        System.out.println("6. Print the History");
                        System.out.println("7. Logout");
                        System.out.println("8. Exit");
                        System.out.println("----------------------------------------------------");
                        System.out.print("Enter choice : ");
                        
                        int s=sc.nextInt();
                        if(s==1)
                            ob[index].show_balance();
                        else if(s==2){
                            System.out.print("\nEnter Amount : ");
                                int with=sc.nextInt();
                                ob[index].withdraw(with);
                        }
                        else if(s==3){
                            System.out.print("\nEnter Amount : ");
                            int depo=sc.nextInt();
                            ob[index].deposit(depo);
                        }
                        else if(s==4)
                        {
                            String receiver;
                            int index1=-1;
                            System.out.print("\nEnter Name of Receiver : ");
                            receiver=sc.next();
                            for(k=0;k<ob[0].start;k++)
                            {
                                if(ob[k].name.equals(receiver)&&k!=index)
                                {
                                    index1=k;
                                    break;
                                }
                            }
                            if(index1!=-1)
                            {
                                System.out.print("Enter Amount : ");
                                int amount=sc.nextInt();
                                ob[index].transfer(ob,index,index1,amount);  
                            }
                            else
                            System.out.println("\nUsername of Receiver doesn't exist!\n");
                        }
                        else if(s==5)
                            ob[index].History(ob,index);
                        else if(s==6)
                        {
                            ob[index].print(ob,index);
                        }
                        else if(s==7){
                            break inner;
                        }
                        else if(s==8)
                            break outer;
                        else
                        System.out.println("\nPlease Enter Correct Choice!\n");
                    }
                 }
                else
                System.out.println("\nUsername and Password didn't match!\n");
                
            }
            else if(c==3){
                end :for(;;){
                    System.out.println("----------------------------------------------------");
                    System.out.println("What do you prefer?\n");
                    System.out.println("1. Show All Accounts");
                    System.out.println("2. Delete Account");
                    System.out.println("3. Logout");
                    System.out.println("----------------------------------------------------");
                    System.out.print("Enter choice : ");
                    int ch=sc.nextInt();
                    if(ch==1){
                        try {
                            FileWriter myWriter = new FileWriter("f1.txt");
                            myWriter.write("------------------------------------------------\n");
                            myWriter.write("         list of account holders \n");
                            myWriter.write("------------------------------------------------\n");
                            for(int x=0;x<ob.length;x++){
                                if(ob[x].name.isEmpty()){
                                    break;
                                }
                                myWriter.write(ob[x].name+"\t\t"+ob[x].balance+"\n");
                                // System.out.println();
                            }
                            myWriter.close();
                            // System.out.println("Successfully wrote to the file.");
                        } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                        }                       
                    }
                    else if(ch==2){
                        System.out.print("\nEnter Name of account you want to delete : ");
                        String delete=sc.next();
                        obj.deleteAcc(ob,delete);
                    }
                    else if(ch==3){
                        break end;
                    }
                    else{
                        System.out.println("\nPlease Enter Correct Choice!\n");
                    }
                }
            }
            else if(c==4)
                break outer;
            else
                System.out.println("\nPlease Enter Correct Choice!\n");
        }
    }
}
