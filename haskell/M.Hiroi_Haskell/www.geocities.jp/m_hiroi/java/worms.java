//
//   worms.java : ミミズのグラフィック
//
//                Copyright (C) 2001 by M.Hiroi
//
// M.Hiroi's Home Page
// URL   : http://www.geocities.co.jp/SiliconValley-Oakland/1680/
// E-Mail: m_hiroi@geocities.co.jp
//

import java.awt.*;
import java.applet.*;
import java.util.*;

// ミミズ本体
class worm {
  int    body_size = 25, tail, width, height;
  double body_x[] = new double [body_size];
  double body_y[] = new double [body_size];
  double x, y, dir;
  Color  color;

  // コンストラクタ
  worm( int w, int h, Color c ){
    width  = w;
    height = h;
    color  = c;
    // ミミズの初期化
    for( int i = 0; i < body_size; i++ ){
      body_x[i] = body_y[i] = -1.0;
    }
    dir  = 0.0;
    tail = 0;
    x = width / 2;
    y = height / 2;
  }

  // 移動
  void move_worm( Graphics g, Random rand ){
    tail = (tail + 1) % body_size;
    delete_body( g, tail );
    if( rand.nextInt() > 0 ){
      dir += 0.18;
    } else {
      dir -= 0.18;
    }
    x += Math.sin( dir ) * 4.0;
    if( x < 0.0 ){
      x += width;
    } else if( x >= width ){
      x -= width;
    }
    y += Math.cos( dir ) * 4.0;
    if( y < 0.0 ){
      y += height;
    } else if( y >= height ){
      y -= height;
    }
    body_x[tail] = x;
    body_y[tail] = y;
    draw_body( g, tail );
  }

  // body を消す
  void delete_body( Graphics g, int pos ){
    int x1 = (int)body_x[pos];
    int y1 = (int)body_y[pos];
    if( x1 >= 0 ){
      g.setColor( Color.black );
      g.drawOval( x1, y1, 10, 10 );
    }
  }

  // body を描く
  void draw_body( Graphics g, int pos ){
    int x1 = (int)body_x[pos];
    int y1 = (int)body_y[pos];
    if( x1 >= 0 ){
      g.setColor( color );
      g.drawOval( x1, y1, 10, 10 );
    }
  }
}

public class worms extends Applet implements Runnable
{
  // 変数定義
  final int N = 4;
  Image double_buffer;
  Graphics g;
  Thread trig = null;
  Random rand;
//  worm w1, w2, w3;
  worm ww[] = new worm[N];
  
  // 初期化
  public void init(){
    int height, width;
    rand = new Random();
    trig = new Thread( this );
    width  = getSize().width;
    height = getSize().height;
    double_buffer = createImage( width, height );
    g = double_buffer.getGraphics();

    // ミミズの初期化
    ww[0] = new worm( width, height, Color.cyan );
    ww[1] = new worm( width, height, Color.red );
    ww[2] = new worm( width, height, Color.yellow );
    ww[3] = new worm( width, height, Color.white );

    // 画面の初期化
    g.setColor( Color.black );
    g.fillRect( 0, 0, width, height );

    trig.start();
  }

  public void paint( Graphics g ){
    g.drawImage( double_buffer, 0, 0, null );
  }

  public void update( Graphics g ){
    paint( g );
  }

  public void run(){
    while( true ){
      try {
	Thread.sleep( 20 );
      } catch( InterruptedException e ){}
      // ミミズの移動
      for( int i = 0; i < N; i++ ){
        ww[i].move_worm( g, rand );
      }
      repaint();
    }
  }
}

