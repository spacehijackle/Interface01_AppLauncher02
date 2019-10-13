package main;

import java.util.Scanner;

import com.launcher.AppLauncherException;
import com.launcher.HistoryLauncher;
import com.launcher.Terminator;
import com.menu.Menu;
import com.menu.MenuItem;
import com.menu.MenuOption;

import launcher.BrowserLauncher;
import launcher.IEFavoriteLauncher;
import launcher.MailerLauncher;


/**
 * アプリランチャープログラム
 *
 * @author t.yoshida
 */
public class Startup
{
	public static void main(String[] args)
	{
		/*
		 * メニューを作成する。
		 */
		Menu menu = new Menu();
		menu.add(new HistoryLauncher("履歴"));
		menu.add(new BrowserLauncher("ググれカス！", "https://www.google.co.jp/"));
		menu.add(new BrowserLauncher("ヤフトピ", "https://news.yahoo.co.jp/"));
		menu.add(new MailerLauncher("メール送信", "webmaster@hogehoge.jp"));
		menu.add(new IEFavoriteLauncher("IEお気に入り"));
		menu.add(new Terminator("終了する"));

		/*
		 * 指定されたメニュー項目に紐づくアプリを起動する。
		 */
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while(true)
		{
			// メニューの表示
			showMenu(menu);

			System.out.print("(*^ ^)v 項目番号の入力 ⇒ ");
			String input = scanner.next();

			// 入力された項目番号から対応するメニュー項目を取得
			MenuItem selected = menu.select(input);
			if(selected != null)
			{
				try
				{
					// メニュー項目に紐づくアプリの起動
					MenuOption option;
					selected.launch(option = new MenuOption());
					if(option.isTerminate())
					{
						// 終了オプションの場合、ループを抜ける
						break;
					}
				}
				catch(AppLauncherException ex)
				{
					// アプリ起動中の例外の表示
					System.out.println();
					System.out.println("(# ﾟДﾟ) アプリ起動に失敗！" + ex.getCause().getMessage());
				}
			}
			else
			{
				// 入力されたメニュー項目番号が不正だった場合
				System.out.println();
				System.out.println("(# ﾟДﾟ) ちゃんと選択して！");
			}

			System.out.println();
		}

		// 終了表示
		System.out.println();
		System.out.println("(*^ ^)v 終了します");
	}

	/**
	 * メニューの表示を行う。
	 */
	private static void showMenu(Menu menu)
	{
		System.out.println("# ────────────────── #");
		System.out.println("           ↑ App Launcher ↑           ");
		System.out.println("# ────────────────── #");
		for(MenuItem item : menu.items())
		{
			if(item.isAvailable())
			{
				String itemNo = String.format("%02d", item.getNo());
				System.out.println("  " + itemNo + ". " + item.getName());
			}
		}
		System.out.println("# ────────────────── #");
		System.out.println();
	}
}
