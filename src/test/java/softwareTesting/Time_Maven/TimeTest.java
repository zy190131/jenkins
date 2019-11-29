package softwareTesting.Time_Maven;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.junit.Test;

public class TimeTest {

	@Test
	public void test1() {
		Time timer = new Time();
		timer.options  = new Options();
			
		//测试 addoption()
		Option help = new Option("h", "help", false, "print help");
		timer.options.addOption(help);
		assertEquals(true, timer.options.hasOption("help"));
	}
	
	@Test
	public void test2() {
		Time timer = new Time();
		timer.options  = new Options();
		
		//测试establish()
		timer.establish();
		
		Option help = new Option("h", "help", false, "print help");
		Option time = new Option("t", "time", false, "print time");
		Option date = new Option("d", "date", false, "print date");
		Option quit = new Option("q", "quit", false, "finish");
		
		assertEquals(help, timer.options.getOption("help"));
		assertEquals(time, timer.options.getOption("time"));
		assertEquals(date, timer.options.getOption("date"));
		assertEquals(quit, timer.options.getOption("quit"));
	}
	
	@Test
	public void test3() {
		//测试打印函数
		Time timer = new Time();
		timer.options  = new Options();
		assertEquals(true, timer.LocalTime());
		assertEquals(true, timer.LocalDate());
	}
	
	@Test
	public void test4() throws ParseException {
		//测试CLI解析阶段
		Time timer = new Time();
		timer.options  = new Options();
		timer.establish();
		CommandLineParser parser = new PosixParser();
        CommandLine commandLine = null;
        
        String args[] = {"-h", "-t", "-d"};
        commandLine = parser.parse(timer.options, args);
        assertEquals(true, commandLine.hasOption('h'));
        assertEquals(true, commandLine.hasOption('t'));
        assertEquals(false, commandLine.hasOption('q'));
	}
	
	@Test
	public void test5() {
		Time timer = new Time();
		timer.options  = new Options();
		timer.establish();
	    
		//进行重定向
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();//把标准输出指定到ByteArrayOutputStream中
	    	PrintStream console = System.out;// 获取System.out 输出流的句柄
	    	System.setOut(new PrintStream(bytes));//将原本输出到控制台Console的字符流重定向到bytes
	    
	    //调用PrintHelp()，测试
	    timer.PrintHelp();
	    String expected = new String ("usage: timer [-d] [-h] [-q] [-t]\n" + 
	    		" -d,--date   print date\n" + 
	    		" -h,--help   print help\n" + 
	    		" -q,--quit   finish\n" + 
	    		" -t,--time   print time\n" + 
	    		"--------------------------------------\n");//PrintHelp()函数的预期输出
	    assertEquals(expected,bytes.toString());
	    System.setOut(console);
	}
	
	
	/*
	 * 以下测试用例是实验三为提升覆盖率补充的测试用例
	 */

	@Test
	public void test7() {
		Time timer = new Time();
		timer.options  = new Options();
		timer.establish();
		
		//对输出进行重定向
		ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();//把标准输出指定到ByteArrayOutputStream中
	      	PrintStream console = System.out;// 获取System.out 输出流的句柄
      	System.setOut(new PrintStream(bytesOut));///将原本输出到控制台Console的字符流重定向到bytes
		
        String args[] = {"-d"};
        timer.use(args);
        
        DateFormat df = DateFormat.getDateInstance();
	    Date date = new Date();
        
      	String expected = new String ("日期：" + df.format(date) + "\n");
      	assertEquals(expected,bytesOut.toString());
      	
      	args[0] = "-s";
      	timer.use(args);
      	expected = new String ("日期：" + df.format(date) + "\n" + 
  	    		"ERROR:Unrecognized option: -s\n");
      	
      	args[0] = "-h";
      	timer.use(args);
      	expected = new String ("日期：" + df.format(date) + "\n" + 
      			"ERROR:Unrecognized option: -s\n" +
      			"usage: timer [-d] [-h] [-q] [-t]\n" + 
  	    		" -d,--date   print date\n" + 
  	    		" -h,--help   print help\n" + 
  	    		" -q,--quit   finish\n" + 
  	    		" -t,--time   print time\n" + 
  	    		"--------------------------------------\n");
      	assertEquals(expected,bytesOut.toString());
      	System.setOut(console);    
	}
	
	@Test
	public void test8() {
		Time timer = new Time();
		timer.options  = new Options();
		timer.establish();
		
		//对输出进行重定向
		ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();//把标准输出指定到ByteArrayOutputStream中
      	PrintStream console = System.out;// 获取System.out 输出流的句柄
      	System.setOut(new PrintStream(bytesOut));//将原本输出到控制台Console的字符流重定向到bytes
		
        String args[] = {"-q"};
      	timer.use(args);
      	String expected = new String ("Thanks for using the program!\n");
      	assertEquals(expected,bytesOut.toString());
      	System.setOut(console);
	}
	
	@Test
	public void test9() {
		Time timer = new Time();
		timer.options  = new Options();
		timer.establish();
		
		//对输出进行重定向
		ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();//把标准输出指定到ByteArrayOutputStream中
      	PrintStream console = System.out;// 获取System.out 输出流的句柄
      	System.setOut(new PrintStream(bytesOut));//将原本输出到控制台Console的字符流重定向到bytes
		
        String args[] = {"-t"};
      	timer.use(args);
      	
      	//获取时间
      	DateFormat tf = DateFormat.getTimeInstance(); 
	    Date date = new Date(); 
      	
	    String expected = new String ("时间：" + tf.format(date) + "\n");
      	assertEquals(expected,bytesOut.toString());
      	System.setOut(console);
	}
	
	@Test
	public void test6() {
		Time timer = new Time();
		timer.options  = new Options();
		timer.establish();
		
		//对输入进行重定向
		String input = "timer -h\nexit\n";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
        	
		//对输出进行重定向
      	ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();//把标准输出指定到ByteArrayOutputStream中
      	PrintStream console = System.out;// 获取System.out 输出流的句柄
      	System.setOut(new PrintStream(bytesOut));//将原本输出到控制台Console的字符流重定向到bytes
      	    
      	timer.input();
      	String expected = new String ("Welcome to Timer!\n" + 
      				"—>" + 
      				"usage: timer [-d] [-h] [-q] [-t]\n" + 
      	    		" -d,--date   print date\n" + 
      	    		" -h,--help   print help\n" + 
      	    		" -q,--quit   finish\n" + 
      	    		" -t,--time   print time\n" + 
      	    		"--------------------------------------\n" + 
      	    		"—>" +
      				"Thanks for using the program!\n");//预期输出
      	assertEquals(expected,bytesOut.toString());
      	System.setOut(console);    
	} 
	
	@Test
	public void test10() {
		Time timer = new Time();
		timer.options  = new Options();
		timer.establish();
		
		//对输入进行重定向
		String input = "tim -h\nt\nexit\n";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
        	
       	//对输出进行重定向
      	ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();//把标准输出指定到ByteArrayOutputStream中
      	PrintStream console = System.out;// 获取System.out 输出流的句柄
      	System.setOut(new PrintStream(bytesOut));//将原本输出到控制台Console的字符流重定向到bytes
      	    
      	timer.input();
      	String expected = new String ("Welcome to Timer!\n" + 
      				"—>" + 
      				"ERROR!\n" + 
      				"usage: timer [-d] [-h] [-q] [-t]\n" + 
      	    		" -d,--date   print date\n" + 
      	    		" -h,--help   print help\n" + 
      	    		" -q,--quit   finish\n" + 
      	    		" -t,--time   print time\n" + 
      	    		"--------------------------------------\n" + 
      	    		"—>" +
      				"ERROR!\n" + 
      				"usage: timer [-d] [-h] [-q] [-t]\n" + 
      	    		" -d,--date   print date\n" + 
      	    		" -h,--help   print help\n" + 
      	    		" -q,--quit   finish\n" + 
      	    		" -t,--time   print time\n" + 
      	    		"--------------------------------------\n" + 
      	    		"—>" +
      				"Thanks for using the program!\n");//预期输出
      	assertEquals(expected,bytesOut.toString());
      	System.setOut(console);
	}
}