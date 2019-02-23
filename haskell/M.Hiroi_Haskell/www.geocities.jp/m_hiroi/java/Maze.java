//
// Maze.java : 迷路の生成と解法のデモ
//
//             Copyright (C) 2001 Makoto Hiroi
//
// M.Hiroi's Home Page
// URL   : http://www.geocities.co.jp/SiliconValley-Oakland/1680/
// E-Mail: m_hiroi@geocities.co.jp
//

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.*;

public class Maze extends Applet implements Runnable, MouseListener {
  // 変数定義
  final int START = 0, EXEC = 1, FINISHED = 2;
  byte map [][];
  int  xx[];
  int  yy[];
  int  dx[] = {2, 0, -2, 0};
  int  dy[] = {0, 2, 0, -2};
  int  sx[] = {1, 0, -1, 0};
  int  sy[] = {0, 1, 0, -1};
  int  dirtable[] = {
    0, 1, 2, 3,  0, 1, 3, 2,  0, 2, 1, 3,  0, 2, 3, 1,
    0, 3, 1, 2,  0, 3, 2, 1,  1, 0, 2, 3,  1, 0, 3, 2,
    1, 2, 0, 3,  1, 2, 3, 0,  1, 3, 0, 2,  1, 3, 2, 0,
    2, 0, 1, 3,  2, 0, 3, 1,  2, 1, 0, 3,  2, 1, 3, 0,
    2, 3, 0, 1,  2, 3, 1, 0,  3, 0, 1, 2,  3, 0, 2, 1,
    3, 1, 2, 0,  3, 1, 0, 2,  3, 2, 0, 1,  3, 2, 1, 0,
  };
  int  width, height, xmax, ymax, maxsite, nsite = 0;
  int  state;
  Image double_buffer;
  Graphics dg;
  Thread trig;
  Random rand;

  // 初期化
  public void init(){
    width  = getSize().width;
    height = getSize().height;
    xmax = width / 4;             // 偶数になること
    ymax = height / 4;            // 同上
    maxsite = xmax * ymax / 4;
    map = new byte [xmax + 1][ymax + 1];
    xx  = new int [maxsite];
    yy  = new int [maxsite];
    double_buffer = createImage( width, height );
    dg = double_buffer.getGraphics();
    rand = new Random();
    state = START;

    // マウス入力
    addMouseListener( this );

    // 画面の初期化
    dg.setColor( Color.black );
    dg.fillRect( 0, 0, width, height );
  }

  public void paint( Graphics g ){
    g.drawImage( double_buffer, 0, 0, null );
  }

  public void update( Graphics g ){
    paint( g );
  }

  public void destroy(){
    trig.stop();
  }

  // 0 から n - 1 までの乱数を生成する
  int get_rand( int n ){
    int r = rand.nextInt() % n;
    if( r < 0 ) r = -r;
    return r;
  }

  // マップの描画
  void draw_map( int x, int y, int k ){
    map[x][y] = (byte)k;
    switch( k ){
      case 0:  dg.setColor( Color.lightGray ); break;
      case 1:  dg.setColor( Color.black ); break;
      default: dg.setColor( Color.red );
    }
    dg.fillRect( x * 4, y * 4, 4, 4 );
  }
  
  void add( int i, int j ){
    xx[nsite] = i; yy[nsite] = j; nsite++;
  }
  
  // マップの初期化
  void init_map(){
    // 初期化
    for( int i = 0; i <= xmax; i++ ){
      for( int j = 0; j <= ymax; j++ ){
        map[i][j] = 1;
      }
    }
    for( int i = 3; i <= xmax - 3; i++ ){
      for( int j = 3; j <= ymax - 3; j++ ){
        draw_map( i, j, 0 );
      }
    }
    draw_map( 2, 3, 0 );
    draw_map( xmax - 2, ymax - 3, 0 );
    // サイトを加える
    for( int i = 4; i <= xmax - 4; i += 2 ){
      add( i, 2 );
      add( i, ymax - 2 );
    }
    for( int j = 4; j <= ymax - 4; j += 2 ){
      add( 2, j );
      add( xmax - 2, j );
    }
    repaint();
  }

  // マップの生成
  void make_map(){
    while( nsite > 0 ){
      int r = get_rand( nsite-- );
      int x = xx[r];
      int y = yy[r];
      xx[r] = xx[nsite];
      yy[r] = yy[nsite];
      while( true ){
        int p = get_rand( 24 );
        int d;
        for( d = 3; d >= 0; d-- ){
          int i = dirtable[p * 4 + d];
          int x1 = x + dx[i];
          int y1 = y + dy[i];
          if( map[x1][y1] == 0 ){
            draw_map( (x + x1) / 2, (y + y1) / 2, 1 );
            x = x1;
            y = y1;
            draw_map( x, y, 1 );
            add( x, y );
            repaint();
            // 休止
            try {
              Thread.sleep( 50 );
            } catch( InterruptedException e ){}
            break;
          }
        }
        if( d < 0 ) break;
      }
    }
  }

  // 迷路を解く
  boolean solve( int x, int y ){
    draw_map( x, y, 2 );
    repaint();
    try {
      Thread.sleep( 50 );
    } catch( InterruptedException e ){}
    if( (x == xmax - 2 ) && (y == ymax - 3) ) return true;  // GOAL
    for( int i = 0; i < 4; i++ ){
      int x1 = x + sx[i];
      int y1 = y + sy[i];
      if( map[x1][y1] == 0 ){
        // 再帰
        if( solve( x1, y1 ) ) return true;
        draw_map( x1, y1, 0 );
        repaint();
        try {
          Thread.sleep( 50 );
        } catch( InterruptedException e ){}
      }
    }
    return false;
  }

  public void run(){
    while( true ){
      init_map();
      make_map();
      solve( 2, 3 );
      state = FINISHED;
      trig.suspend();  // スレッド一時停止
    }
  }

  // マウスのイベント処理
  public void mouseEntered(MouseEvent e){};
  public void mouseExited(MouseEvent e){};
  public void mousePressed(MouseEvent e){};
  public void mouseReleased(MouseEvent e){};

  // マウスクリック
  public void mouseClicked(MouseEvent e){
    // スタート
    if( trig == null ){
      trig   = new Thread( this );
      trig.start();
    } else {
      if( state == FINISHED ){
        state = EXEC;
        trig.resume();    // スレッド再開
      }
    }
  }
}

