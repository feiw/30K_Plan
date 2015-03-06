package fei;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.Vector;

public class Util {
	

	  public static BufferedReader openBufferedReader(String fileName) throws IOException
	  {
	    BufferedReader reader=null;
	 
	    if (fileName != null)
	      {
	        String charSet= "UTF-8";
	    	try
	    	{
	           reader=new BufferedReader(new InputStreamReader(new FileInputStream(fileName),charSet));
	    	}
	    	catch (Throwable e)
	    	{
	    		throw new IOException ("Unable to open file " + fileName + ".  File is most likely not charset " + charSet + ".");
	    	}
	      }
	    else
	      {
	        throw new IOException("openBufferedReader: null filename");
	      }
	    return(reader);
	  }

	  public static boolean fileExists(String fileName)
	  {
	    File fn = new File(fileName);
	    return(fn.exists());
	  }

	  public static Vector<String> loadFile(String fileName
              ,String commentTag) throws IOException
      {
			String         s;
			Vector  <String>   temp;
			BufferedReader buf=null;
			
			// If the file does not exist, return null.
			if (fileExists(fileName) == false)
			{
				return(null);
			}
		
			try
			{
				temp=new Vector <String> (100);
				buf=openBufferedReader(fileName);  
				do
				{
					s=buf.readLine();
					if (s != null)
					{
						if ((commentTag != null) && s.startsWith(commentTag))
						{
							continue;
						}
						temp.addElement(s);
					}
				}
				while (s != null);
			}
			catch (IOException e)
			{
				throw new IOException("Error loading file="+fileName+" "+e.getMessage());
			}
			finally
			{
				if (buf != null)
				{
					try {buf.close();} catch(IOException e) {}
				}	
			}
			
			return(temp);
		}	  

	    public static String[] getTokens(String str,char delim)
	    {
	      int currentPosition=0;
	      int maxPosition=str.length();
	      int count=1;
	      int i;
	      for(i=0;i<maxPosition;++i)
	      {
	        if(str.charAt(i)==delim)
	        {
	          ++count;
	        }
	      }
	      String[] sa=new String[count];
	      int start;
	      i=0;
	      while(currentPosition<maxPosition)
	      {
	        start=currentPosition;
	        while(currentPosition<maxPosition
	              &&delim!=str.charAt(currentPosition))
	        {
	          currentPosition++;
	        }
	        sa[i++]=str.substring(start,currentPosition);
	        ++currentPosition;
	      }
	      i=count-1;
	      if(sa[i]==null)
	      {
	        sa[i]="";
	      }
	      return(sa);
	    }
}
