import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

public class MazeFrame extends MyFrame {

	// 迷路の情報を格納する2次元配列
	private int[][] _mazeData;

	// 迷路の横
	private int _width = 25;
	// 迷路の縦
	private int _height = 25;

	// Cellを格納するVector配列
	private Vector<Cell> _cells;
	
	// 迷路の生成処理(未完成)
	private void mazeGenerate() {
		
		_mazeData = new int[_height][_width];

		// 外周壁、スタート、ゴールの設置処理
		for (int y = 0; y < _height; y++) {
			for (int x = 0; x < _width; x++) {

				int cellType = 0;
				// 外周を壁にする
				if (y == 0 || x == 0 || y == _height - 1 || x == _width - 1) {
					cellType = 1;
				}
				// スタート地点は左上
				else if (y == 1 && x == 1) {
					cellType = 2;
				}
				// ゴールは右下
				else if (y == _height - 2 && x == _width - 2) {
					cellType = 3;
				}
				_mazeData[y][x] = cellType;
			}
		}
		// まずすべての偶数マス（縦横）に壁を立てる（棒）
		for (int y = 2; y < _height - 2; y += 2) {
			for (int x = 2; x < _width - 2; x += 2) {
				if (_mazeData[y][x] == 0) {
					_mazeData[y][x] = 1; // 棒を立てる（壁）
				}
			}
		}

		// 棒倒し（各柱から1方向へ倒す）
		for (int y = 2; y < _height - 2; y += 2) {
			for (int x = 2; x < _width - 2; x += 2) {

				int[][] dirs;
				if (y == 2) {
					dirs = new int[][] {
						{-1, 0}, // 上
						{1, 0},  // 下
						{0, -1}, // 左
						{0, 1}   // 右
					};
				} else {
					dirs = new int[][] {
						{1, 0},  // 下
						{0, -1}, // 左
						{0, 1}   // 右
					};
				}

				// ランダムな順番で試す
				Collections.shuffle(Arrays.asList(dirs));
				for (int[] dir : dirs) {
					int nx = x + dir[1];
					int ny = y + dir[0];
					if (nx <= 0 || ny <= 0 || nx >= _width - 1 || ny >= _height - 1) continue;

					if (_mazeData[ny][nx] == 0) {
						_mazeData[ny][nx] = 1; // 壁を倒す
						break;
					}
				}
			}
		}
	}
	

	

	

	public void run() {

		/*_cells = new Vector<Cell>();
		mazeGenerate();
		for (int y = 1; y < _height - 1; y++) {
			for (int x = 1; x < _width - 1; x++) {
				if (_mazeData[y][x] == 0 && Math.random() < 0.3) { // 30%の確率で壁を置く
					_mazeData[y][x] = 1; // 壁
				}
			}
		}

		for (int y = 0; y < _height; y++) {
			for (int x = 0; x < _width; x++) {
				_cells.add(new Cell(x, y, _mazeData[y][x]));
			}
		}

		while (true) {
			clear();
			for (int i = 0; i < _cells.size(); i++) {
				_cells.get(i).draw(this);
			}

			sleep(0.1f);
		}
	}*/
		_cells = new Vector<Cell>();
		mazeGenerate();

		for (int y = 0; y < _height; y++) {
			for (int x = 0; x < _width; x++) {
				_cells.add(new Cell(x, y, _mazeData[y][x]));
			}
		}

		while (true) {
			clear();
			for (int i = 0; i < _cells.size(); i++) {
				_cells.get(i).draw(this);
			}

			sleep(0.1f);
		}}
}
