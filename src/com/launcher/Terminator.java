package com.launcher;

import com.menu.MenuOption;


/**
 * アプリランチャー メニュー終了用
 *
 * @author t.yoshida
 */
public class Terminator implements AppLauncher
{
	// アプリ起動名
	private String _name;

	/**
	 * アプリ起動名を指定して {@link Terminator} を生成する。
	 *
	 * @param name アプリ起動名
	 */
	public Terminator(String name)
	{
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
		option.terminate();
	}
}
