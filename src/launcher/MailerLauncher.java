package launcher;

import java.awt.Desktop;
import java.net.URI;

import com.launcher.AppLauncher;
import com.menu.MenuOption;

/**
 * メーラーを起動する。
 *
 * @author t.yoshida
 */
public class MailerLauncher implements AppLauncher
{
	// アプリ起動名
	private String _name;

	// 送信先アドレス
	private String _mailto;

	/**
	 * アプリ起動名と送信先アドレスを指定して {@link MailerLauncher} を生成する。
	 *
	 * @param mailto 送信先アドレス
	 */
	public MailerLauncher(String name, String mailto)
	{
		_mailto = mailto;
		_name = name;
	}

	@Override
	public boolean isAvailable()
	{
		return true;
	}

	@Override
	public String getName()
	{
		return _name;
	}

	@Override
	public void launch(MenuOption option) throws Exception
	{
		Desktop desktop = Desktop.getDesktop();
		desktop.mail(new URI("mailto:" + _mailto));
	}
}
