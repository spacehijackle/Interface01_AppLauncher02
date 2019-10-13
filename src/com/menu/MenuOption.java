package com.menu;


/**
 * メニューオプション
 *
 * @author t.yoshida
 */
public class MenuOption
{
	// 終了フラグ
	private boolean _isTerminate;

	/**
	 * メニューを終了する。
	 */
	public void terminate()
	{
		_isTerminate = true;
	}

	/**
	 * メニューを終了するか否かを返す。
	 *
	 * @return true: 終了する
	 */
	public boolean isTerminate()
	{
		return _isTerminate;
	}
}
