package com.menu;

import com.launcher.AppLauncher;
import com.launcher.AppLauncherException;


/**
 * メニュー項目クラス
 *
 * @author t.yoshida
 */
public class MenuItem
{
	// 項目番号
	private int _no;

	// アプリランチャー
	private AppLauncher _launcher;

	// アプリ起動証跡
	private Menu.AppLaunchTracer _tracer;

	/**
	 * メニュー項目を生成する。
	 *
	 * @param no 項目番号
	 * @param launcher アプリランチャー
	 * @param tracer アプリ起動証跡
	 */
	MenuItem(int no, AppLauncher launcher, Menu.AppLaunchTracer tracer)
	{
		_no = no;
		_launcher = launcher;
		_tracer = tracer;
	}

	/**
	 * 項目番号を返す。
	 *
	 * @return 項目番号
	 */
	public int getNo()
	{
		return _no;
	}

	/**
	 * 項目名を返す。
	 *
	 * @return 項目名
	 */
	public String getName()
	{
		return _launcher.getName();
	}

	/**
	 * アプリの起動が可能か否かを返す。
	 *
	 * @return true: アプリの起動が可能な場合
	 */
	public boolean isAvailable()
	{
		return _launcher.isAvailable();
	}

	/**
	 * アプリを起動する。
	 *
	 * @exception アプリ起動中の例外
	 */
	public void launch(MenuOption option) throws AppLauncherException
	{
		try
		{
			_launcher.launch(option);

			// アプリ起動の証跡取り
			_tracer.capture(_launcher);
		}
		catch(Exception ex)
		{
			throw new AppLauncherException(ex);
		}
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + _no;
		return result;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * 項目番号が等しいか否かで比較する。
	 * </p>
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuItem other = (MenuItem) obj;
		if (_no != other._no)
			return false;
		return true;
	}
}
