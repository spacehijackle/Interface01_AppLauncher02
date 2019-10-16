package com.menu;

import java.util.ArrayList;
import java.util.List;

import com.launcher.AppLauncher;
import com.launcher.HistoryLauncher;

/**
 * メニュークラス
 *
 * @author t.yoshida
 */
public class Menu
{
	// メニュー項目リスト
	private List<MenuItem> _items;

	// アプリ起動証跡
	private AppLaunchTracer _tracer;

	// アプリランチャー履歴
	private HistoryLauncher _historyLauncher;

	/**
	 * メニューを生成する。
	 */
	public Menu()
	{
		_items = new ArrayList<>();
		_tracer = new AppLaunchTracer();
	}

	/**
	 * メニュー項目を追加する。
	 *
	 * @param launcher アプリランチャー
	 */
	public void add(AppLauncher launcher)
	{
		// 項目番号を設定してリストに追加
		int itemNo = _items.size();
		_items.add(new MenuItem(itemNo, launcher, _tracer));
	}

	/**
	 * メニュー項目を追加する。
	 *
	 * @param historyLauncher アプリランチャー履歴
	 */
	public void add(HistoryLauncher historyLauncher)
	{
		_historyLauncher = historyLauncher;
		add((AppLauncher)historyLauncher);
	}

	/**
	 * メニュー項目リストを返す。
	 *
	 * @return メニュー項目リスト
	 */
	public List<MenuItem> items()
	{
		return (new ArrayList<>(_items));
	}

	/**
	 * 入力されたメニュー項目番号に対応するメニュー項目を返す。
	 *
	 * @param inputItemNo 入力されたメニュー項目番号
	 * @return メニュー項目（該当するメニュー項目がない場合、null）
	 */
	public MenuItem select(String inputItemNo)
	{
		MenuItem selected = null;
		try
		{
			int itemNo = Integer.parseInt(inputItemNo);

			for(MenuItem item : _items)
			{
				if(itemNo == item.getNo())
				{
					if(item.isAvailable())
					{
						selected = item;
					}
					break;
				}
			}
		}
		catch(NumberFormatException ex) { }

		return selected;
	}

	/**
	 * アプリ起動の証跡を取るクラス
	 */
	public class AppLaunchTracer
	{
		/**
		 * 実行済みアプリ起動を捕捉する。
		 *
		 * @param launcher アプリ起動
		 */
		public void capture(AppLauncher launcher)
		{
			if(_historyLauncher != null)
			{
				_historyLauncher.storeAsHistory(launcher);
			}
		}
	}
}
