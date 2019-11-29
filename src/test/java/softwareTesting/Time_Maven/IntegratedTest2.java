package softwareTesting.Time_Maven;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.util.Date;

import org.apache.commons.cli.Options;
import org.junit.Test;

public class IntegratedTest2 {

	@Test
	public void test1() {
		Time2 timer = new Time2();

		timer.options  = new Options();
		timer.establish();
	    
		//进行重定向
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();//把标准输出指定到ByteArrayOutputStream中
	    PrintStream console = System.out;// 获取System.out 输出流的句柄
	    System.setOut(new PrintStream(bytes));//将原本输出到控制台Console的字符流重定向到bytes
	    
	    String args[] = {"-h", "-t"};
	    timer.use(args);
	    String expected1 = new String ("Help information is printed!\n" + 
	    							   "LocalTime is printed!\n");
	    assertEquals(expected1,bytes.toString());
	    System.setOut(console);
	}
	
	@Test
	public void test2() {
		Time timer = new Time();

		timer.options  = new Options();
		timer.establish();
	    
		//进行重定向
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();//把标准输出指定到ByteArrayOutputStream中
	    PrintStream console = System.out;// 获取System.out 输出流的句柄
	    System.setOut(new PrintStream(bytes));//将原本输出到控制台Console的字符流重定向到bytes
	    
	    String args[] = {"-h", "-d"};
	    timer.use(args);
	    
	    DateFormat df = DateFormat.getDateInstance();
	    Date date = new Date();
	    
	    String expected1 = new String ("usage: timer [-d] [-h] [-q] [-t]\n" + 
	    							   " -d,--date   print date\n" + 
	    							   " -h,--help   print help\n" + 
	    							   " -q,--quit   finish\n" + 
	    							   " -t,--time   print time\n" + 
	    							   "--------------------------------------\n" + 
	    							   "日期：" + df.format(date) + "\n");
	    assertEquals(expected1,bytes.toString());
	    System.setOut(console);
	}
}
