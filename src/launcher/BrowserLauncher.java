package launcher;

import java.awt.Desktop;
import java.net.URI;

import com.launcher.AppLauncher;
import com.menu.MenuOption;

/**
 * ブラウザを起動する。
 *
 * @author t.yoshida
 */
public class BrowserLauncher implements AppLauncher
{
	// アプリ起動名
	private String _name;

	// URL
	private String _url;

	/**
	 * アプリ起動名、URLを指定して {@link BrowserLauncher} を生成する。
	 *
	 * @param url URL
	 */
	public BrowserLauncher(String name, String url)
	{
		_url = url;
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
		desktop.browse(new URI(_url));
	}
}
