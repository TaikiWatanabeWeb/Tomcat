package test_tomcat_git;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Text
{
	DataBaseConnect DBC = new DataBaseConnect();

	BufferedReader br;
	FileWriter filewriter;
	BufferedWriter bw;
	PrintWriter pw;

	int[] playerinfo = new int[10];//配列数は仮設定

	//int roomid,playernumber;//ルーム番号をintにキャスト

	File file;

	int[] editer(int room,int number,int linenumber,int WriteorRead,int[] rewrite)//試験的に作るため呼び出し禁止 room 部屋番号　number プレイヤー番号 書き換える配列
	{
		String[] line = new String[10];//配列数は仮設定、各行の情報が入力
		file = new File("");//roomidとplayernumberを使用してファイルを特定

		//以下テキストファイル読み込み
		try
		{
			br = new BufferedReader(new FileReader(file));
			String str = br.readLine();

			for(int i = 0;str != null;i++)
			{
				line[i] = str;
			}
		}

		catch(Exception e)
		{
			//何かしらレスポンスを記述
		}

		finally
		{
			close();
		}

		if(WriteorRead == 0)
		{
			filewriter(line,linenumber,rewrite);//行配列、行番号、書き込む配列
			return null;
		}
		else
		{
			filereader(line,linenumber);//行配列、行番号
			return playerinfo;
		}
	}

	private void filewriter(String[] lineinfo,int linenumber,int[] write) //ファイル書き込み
	{
		String text = "";
		String linestr = "";
		for(int i = 0;i<write.length;i++)
		{
			linestr = linestr+write[i];
			if(i<write.length-1)
			{
				linestr = linestr+",";
			}
		}

		lineinfo[linenumber] = linestr;

		for(int i = 0;i<lineinfo.length;i++)
		{
			text = text+lineinfo[i];

			if(i<lineinfo.length-1)
			{
				text = text+"\r\n";
			}
		}

		try
		{
			filewriter = new FileWriter(file);

			bw = new BufferedWriter(filewriter);
			pw = new PrintWriter(bw);
			pw.println(text);
		}

		catch(Exception e)
		{

		}

		finally
		{
			close();
		}
	}

	private void filereader(String[] lineinfo,int linenumber) //ファイル読み込み
	{
		//カンマ区切り解除
		String[] temporary = lineinfo[linenumber].split(",");

		//以下int配列へキャスト
		for(int i = 0;i<temporary.length;i++)
		{
			playerinfo[i] = Integer.parseInt(temporary[i]);
		}
	}

	void close()
	{
		if(br!=null)
		{
			try
			{
				br.close();
			}

			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		if(bw != null)
		{
			try
			{
				bw.close();
			}

			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
