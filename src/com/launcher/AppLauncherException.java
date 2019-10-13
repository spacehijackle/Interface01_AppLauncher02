package com.launcher;

/**
 * アプリ起動例外クラス
 *
 * @author t.yoshida
 */
public class AppLauncherException extends Exception
{
	/**
	 * アプリ起動例外を生成する。
	 *
	 * @param cause 原因
	 */
	public AppLauncherException(Throwable cause)
	{
		super(cause);
	}
}
