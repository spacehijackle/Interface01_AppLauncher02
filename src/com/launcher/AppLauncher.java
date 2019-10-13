package com.launcher;

import com.menu.MenuOption;

/**
 * アプリランチャー インターフェース定義
 *
 * @author t.yoshida
 */
public interface AppLauncher
{
	/**
	 * アプリ起動が可能か否かを返す。
	 *
	 * @return true: アプリ起動が可能な場合
	 */
	boolean isAvailable();

	/**
	 * アプリ起動名を返す。
	 *
	 * @return アプリ起動名
	 */
	String getName();

	/**
	 * アプリを起動する。
	 *
	 * @param option メニューオプション
	 * @exception アプリ起動中の例外
	 */
	void launch(MenuOption option) throws Exception;
}
