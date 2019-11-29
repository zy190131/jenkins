package softwareTesting.Time_Maven;

import java.text.DateFormat;
import java.util.Date;
import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

public class Time {
	public Options options;
	
	//创建参数选项
	public void establish() {
		options  = new Options();
		Option help = new Option("h", "help", false, "print help");
		Option time = new Option("t", "time", false, "print time");
		Option date = new Option("d", "date", false, "print date");
		Option quit = new Option("q", "quit", false, "finish");
		options.addOption(help);
		options.addOption(time);
		options.addOption(date);
		options.addOption(quit);
	}
	
	public int use(String args[]) {
		
		//解析阶段
		//自动生成帮助语句
		HelpFormatter hf = new HelpFormatter();
        hf.setWidth(110);
        
        //创建解析器
        CommandLineParser parser = new PosixParser();
        //解析命令行
        CommandLine commandLine = null;
        try {
            commandLine = parser.parse(options, args);
            
            /*
            //打印各个选项的名称与值
            Option[] opts = commandLine.getOptions();
            if (opts != null) {
                for (Option opt1 : opts) {
                    String name = opt1.getLongOpt();
                    String value = commandLine.getOptionValue(name);
                    System.out.println(name + "=>" + value);
                }
            }
            */
            
            //处理阶段
            if (!commandLine.hasOption('q')) {
            	System.out.println("Thanks for using the program!");
            	//System.exit(0);
            	return 1;
            }
            
            //打印使用帮助
            if (commandLine.hasOption('h')) {
            	PrintHelp();
            }
            
            //打印当前时刻
            if(commandLine.hasOption('t')) {
            	LocalTime();
            }
            
            //打印当前日期
            if(commandLine.hasOption('d')) {
            	LocalDate();  
            }
                      
        }catch (ParseException exp) {
        	//解析失败
        	System.out.println("ERROR:" + exp.getMessage());
        	//PrintHelp();
        }
        return 0;
	}
	
	public boolean PrintHelp() {
		HelpFormatter hf = new HelpFormatter();
		hf.printHelp("timer", options, true);
        System.out.println("--------------------------------------");
        return true;
	}
	
	public boolean LocalTime() {
		DateFormat tf = DateFormat.getTimeInstance(); 
	    Date date = new Date(); 
		System.out.println("时间：" + tf.format(date));
		return true;
	}
	
	public boolean LocalDate() {
		DateFormat df = DateFormat.getDateInstance();
	    Date date = new Date();  
		System.out.println("日期：" + df.format(date));
		return true;
	}
	
	public void input() {
		System.out.println("Welcome to Timer!");
		
		Scanner scanner = new Scanner(System.in);
		String input = null;
		do {
			System.out.print("—>");
			input = scanner.nextLine();
			
			//exit 结束
			if(input.equalsIgnoreCase("exit")) {
				System.out.println("Thanks for using the program!");
				break;
			}
			
			//如果不是以 timer 开头，则出错
			try {
				String first = input.substring(0, 5);
				if(!first.equals("timer")) {
					System.out.println("ERROR!");
		        	PrintHelp();
		        	continue;
				}
			}catch(Exception exp) {
				System.out.println("ERROR!");
	        	PrintHelp();
	        	continue;
			}
						
			//分割字符串
			String arg[] = (input.substring(6)).split("\\s+");
			/*for(String ss : arg){
				System.out.println(ss);
			}*/
			int value = use(arg);		
			if(value == 1) break;
			
		}while(true);
		scanner.close();
		return ;
	}
	
	public static void main(String[] args) {
		Time timer = new Time();
		timer.establish();
		timer.input();
    }
}
