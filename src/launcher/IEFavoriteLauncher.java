package launcher;

import java.util.Map;

import com.launcher.AppLauncher;
import com.menu.MenuOption;

/**
 * ＩＥのお気に入りフォルダを開く。
 *
 * @author t.yoshida
 */
public class IEFavoriteLauncher implements AppLauncher
{
	// アプリ起動名
	private String _name;

	/**
	 * アプリ起動名を指定して {@link IEFavoriteLauncher} を生成する。
	 */
	public IEFavoriteLauncher(String name)
	{
		_name = name;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Windows OS のみ利用可能
	 * </p>
	 */
	@Override
	public boolean isAvailable()
	{
		String os = System.getProperty("os.name").toLowerCase();
		return (os.startsWith("windows"));
	}

	@Override
	public String getName()
	{
		return _name;
	}

	@Override
	public void launch(MenuOption option) throws Exception
	{
		// 環境変数の取得
		Map<String, String> envMap = new ProcessBuilder().environment();

		// エクスプローラでお気に入りを表示
		String[] cmd = { "explorer", envMap.get("USERPROFILE") + "\\Favorites" };
		new ProcessBuilder(cmd).start();
	}
}
