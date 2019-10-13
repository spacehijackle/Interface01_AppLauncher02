package com.launcher;

import java.util.ArrayList;
import java.util.List;

import com.menu.MenuOption;

/**
 * 実行済みアプリを再実行する。
 *
 * @author t.yoshida
 */
public class HistoryLauncher implements AppLauncher
{
	// 最大表示項目名文字列長
	private static final int MAX_DISPLAYED_NAME_LENGTH = 20;

	// アプリ起動名
	private String _name;

	// アプリランチャーの履歴
	private List<AppLauncher> _history;

	/**
	 * アプリ起動名を指定して {@link HistoryLauncher} を生成する。
	 *
	 * @param name アプリ起動名
	 */
	public HistoryLauncher(String name)
	{
		_name = name;
		_history = new ArrayList<>();
	}

	/**
	 * 実行済みのアプリランチャーを保管する。
	 *
	 * @param launcher アプリランチャー
	 */
	public void storeAsHistory(AppLauncher launcher)
	{
		// 追加対象が当クラスの場合ははじく
		if(launcher == this) return;

		// 同じアプリランチャーが連続する場合ははじく
		if(hasHistory() && launcher == last()) return;

		_history.add(launcher);
	}

	/**
	 * 履歴の末尾を返す。
	 *
	 * @return 履歴の末尾
	 */
	private AppLauncher last()
	{
		if(!hasHistory()) return null;

		int idxLast = _history.size() - 1;
		return _history.get(idxLast);
	}

	/**
	 * 履歴があるか否かを返す。
	 *
	 * @return true: 履歴がある
	 */
	private boolean hasHistory()
	{
		return (_history.size() > 0);
	}

	@Override
	public boolean isAvailable()
	{
		return hasHistory();
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * 起動したアプリの履歴数とその項目名を追記する。
	 * </p>
	 */
	@Override
	public String getName()
	{
		StringBuilder sb = new StringBuilder(50);
		sb.append(_name);
		sb.append(" (").append(_history.size()).append(")");
		for(AppLauncher launcher : _history)
		{
			sb.append(" > ").append(launcher.getName());
		}

		// メニュー項目名の長さ調節
		if(sb.length() > MAX_DISPLAYED_NAME_LENGTH)
		{
			sb.delete(MAX_DISPLAYED_NAME_LENGTH - 1, sb.length());
			sb.append("…");
		}

		return sb.toString();
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * 履歴の先頭を再実行する。
	 * </p>
	 */
	@Override
	public void launch(MenuOption option) throws Exception
	{
		AppLauncher head = _history.remove(0);
		head.launch(option);
	}
}
