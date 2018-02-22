
import java.io.*;
import javax.swing.*;

public class RandomFilesEx1
{
   public static void main(String[] args)
   {
     store();
     display();
     seekrec();
   }
   
   
   
   public static void store()
   {
     try
      {
         RandomAccessFile os = new RandomAccessFile("fees.dat", "rw");
       
         os.setLength(0);  

         String temp = JOptionPane.showInputDialog(null, "Enter a level [-1 to stop]");
         int level = Integer.parseInt(temp);
         
         while(level != -1)
 	{
        
         String temp2 = JOptionPane.showInputDialog(null, "Enter a fee");
          double fee = Double.parseDouble(temp2);
          
          os.writeInt(level);
          os.writeDouble(fee);
      
          temp = JOptionPane.showInputDialog(null, "Enter a level [-1 to stop]");
          level = Integer.parseInt(temp);
       }	  
         os.close( );
      }

      catch(IOException e)
      {
         System.out.println(e.getMessage());
      }
   }

public static void display()
  {
     try
      {
        RandomAccessFile is = new RandomAccessFile("fees.dat", "r");
        
          
        try
        {
          while (true)
            {
             int level = is.readInt();
             double fee = is.readDouble();
             System.out.println(level + "    "+fee);
            }
        } 
      
        catch(EOFException e)
         {
            JOptionPane.showMessageDialog(null, "End of File reached");
         }
        is.close( );
      }     
        
      catch(FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, "File Not Found");
        }
        
      catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "IO Error");
        }
   }  
      
  public static void seekrec()	
	  {
	  	try
		 {
			RandomAccessFile is = new RandomAccessFile("fees.dat", "r");
			int n = 3 ; //record to seek
			
			try
			  {	
				
				is.seek((long)((n-1)* 12));    //4 for int + 8 for double = 12
				int level = is.readInt();
				double fee = is.readDouble();
				System.out.println("\nLevel: "+ level +"\t"+ "Fee: "+ fee);
			  }
			  
			
			catch (EOFException e)
			  {
				JOptionPane.showMessageDialog(null,"End of File");
			  }
			is.close();
		 }
			
		 catch (FileNotFoundException e)
			{
				JOptionPane.showMessageDialog(null,"File not found");
			}
		
		 catch (IOException e)
			{
				JOptionPane.showMessageDialog(null,"IO error");
			}
	  }   
}
