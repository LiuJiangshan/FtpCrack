import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/** 密码提交到服务器 */
public class SubmitPwd extends Thread
{
	String pwd;

	public SubmitPwd(String pwd)
	{
		this.pwd = pwd;
	}

	@Override
	public void run()
	{
		HttpURLConnection con = null;
		BufferedReader reader = null;
		try
		{
			con = (HttpURLConnection) new URL(Main.SUBMIT_ADDRESS + pwd).openConnection();
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String reStr = reader.readLine();
			if (reStr.equals("ok"))
			{
				System.out.println("提交成功");
				System.exit(0);
			}
			else
				new SubmitPwd(pwd).start();
		}
		catch (Exception e)
		{
			System.out.println("提交失败,重试");
			new SubmitPwd(pwd).start();
		}
		finally
		{
			if (reader != null)
				try
				{
					reader.close();
				}
				catch (IOException e)
				{
				}
			if (con != null)
				con.disconnect();
		}
		super.run();
	}
}
