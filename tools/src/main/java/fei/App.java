package fei;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String sourceFile = "C:\\document\\company\\PCR\\MCD\\PTR\\40966_40967_40970\\40967.csv";
    	String filterFile = "C:\\document\\company\\PCR\\MCD\\PTR\\40966_40967_40970\\40967.txt";
    	String commentTag = "#";
    	StringBuffer accumidList = new StringBuffer();
    	try
    	{
    		Vector <String> source = Util.loadFile(sourceFile, commentTag);
    		Vector <String> filter = Util.loadFile(filterFile, commentTag);
    		for (int i = 0; i < source.size(); i ++ )
    		{
    			String empno = null, line1 = null, line2 = null, empno2 = null, accumid = null;
    			double total =0 ,total2;
    			double remain = 0, remain2 ;
    			line1 = source.elementAt(i);
    			String[] s = Util.getTokens(line1, ',');
    			if (s.length >=5)
    			{
    				empno = s [0].trim().replaceAll("\"", "");
    				total = new Double(s[3].trim()).doubleValue();
    				remain = new Double(s[5].trim()).doubleValue();
    				accumid = s[2].trim().replaceAll("\"", "");
    				//System.out.print("'" + s[1]+"',");
    			}
    			int ss = 0;
    			for (int j = filter.size() -1 ; j >=0 ; j--)
    			{
    				line2 = filter.elementAt(j);
    				String[] s2 = Util.getTokens(line2, ',');
    				empno2 = s2[0].trim();
    				total2 = new Double(s2[1].trim()).doubleValue();
    				remain2 = new Double(s2[2].trim()).doubleValue();    
    				if (empno.equals(empno2) && total == total2 && remain == remain2 )
    				{
    					System.out.println(line1);
    					accumidList.append("'" + accumid + "',");
    					filter.remove(j);
    					break;
    				}
    				else if (empno.equals(empno2) && total == total2)
    				{
    					System.out.println("P----:" + line1);
    				}
    			}

    			
    		}
			System.out.println("Accum ID List : " + accumidList.toString());    	
			System.out.println("Left " + filter.size() + " Records");
    		/*
    		System.out.println("NOT FOUND LIST:");
    		for (int i = 0; i < filter.size(); i ++ )
    		{
    			System.out.println(filter.elementAt(i));
    		}
    		*/
    		
    	}
    	catch(IOException e)
    	{
    		System.out.println(e.getMessage());
    	}
    	
    }
 
}
