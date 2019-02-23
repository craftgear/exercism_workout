//
// LinePuz.java : ラインパズル
//
//                Copyright (C) 2001 Makoto Hiroi
//
// M.Hiroi's Home Page
// URL   : http://www.geocities.co.jp/SiliconValley-Oakland/1680/
// E-Mail: m_hiroi@geocities.co.jp
//
// 盤面
//   0 1 2 3 4 5
// 0 W W W W W W
// 1 W W W W S W
// 2 W A B A B W
// 3 W C D C D W
// 4 W A B A B W
// 5 W C D C D W
// 6 W W W W W W
//

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.*;

public class LinePuz extends Applet implements MouseListener, ActionListener {
  final int  BW = 6, BH = 7, PSIZE = 60;
  final char WALL = 0, ATYPE = 1, BTYPE = 2, CTYPE = 3, DTYPE = 4, SPACE = 5;
  char board[][] = new char [BW] [BH];
  int startAngle[] = {0, 90, 0, 180, 270};
  int width, height, count;
  Button reset;
  Label  message;
  Image double_buffer;
  Graphics dg;

  public void init(){
    // 画面の初期化
    width = getSize().width;
    height = getSize().height;
    double_buffer = createImage( width, height );
    dg = double_buffer.getGraphics();

    dg.setColor( Color.lightGray );
    dg.fillRect( 0, 0, width, height );

    // ボタン
    setLayout( new FlowLayout( FlowLayout.CENTER ) );
    reset = new Button("Reset");
    reset.setFont( new Font( "Dialog", Font.PLAIN, 20 ) );
    add( reset );
    reset.addActionListener(this);

    // ラベル
    message = new Label("手数  0  ");
    message.setFont( new Font( "Dialog", Font.PLAIN, 25 ) );
    message.setBackground( Color.lightGray );
    add( message );

    // マウス入力
    addMouseListener( this );

    // 盤面の初期化
    init_board();
  }

  // 盤面の初期化
  void init_board(){
    for( int i = 0; i < BW; i++ ){
      for( int j = 0; j < BH; j++ ){
        board[i][j] = WALL;
      }
    }
    board[4][1] = SPACE;
    board[1][2] = board[3][2] = board[1][4] = board[3][4] = ATYPE;
    board[2][2] = board[4][2] = board[2][4] = board[4][4] = BTYPE;
    board[1][3] = board[3][3] = board[1][5] = board[3][5] = CTYPE;
    board[2][3] = board[4][3] = board[2][5] = board[4][5] = DTYPE;
    // 描画
    for( int i = 0; i < BW; i++ ){
      for( int j = 0; j < BH; j++ ){
        paint_piece( i, j );
      }
    }
  }

  public void paint( Graphics g ){
    g.drawImage( double_buffer, 0, 0, null );
  }

  public void update( Graphics g ){
    paint( g );
  }

  // ピースを描く
  public void paint_piece( int x, int y ){
    int x1 = PSIZE * x, y1 = PSIZE * y;
    int x2 = x1 + 2, y2 = y1 + 2;
    int piece = board[x][y];
    if( piece != WALL ){
      dg.setColor( Color.white );
      dg.fillRect( x1, y1, PSIZE - 1, PSIZE - 1 );
      if( piece != SPACE ){
        dg.setColor( Color.red );
        switch( piece ){
        case BTYPE: x2 -= PSIZE; break;
        case CTYPE: y2 -= PSIZE; break;
        case DTYPE: x2 -= PSIZE; y2 -= PSIZE; break;
        }
        dg.fillArc( x2, y2, PSIZE * 2 - 4, PSIZE * 2 - 4, startAngle[piece], 90 );
        dg.setColor( Color.white );
        dg.fillArc( x2 + 3, y2 + 3, PSIZE * 2 - 10, PSIZE * 2 - 10, startAngle[piece], 90 );
      }
      dg.setColor( Color.lightGray );
      dg.drawRect( x1, y1, PSIZE - 1, PSIZE - 1 );
    }
  }

  // マウスのイベント処理
  public void mouseEntered(MouseEvent e){};
  public void mouseExited(MouseEvent e){};
  public void mousePressed(MouseEvent e){};
  public void mouseReleased(MouseEvent e){};

  // マウスクリック
  public void mouseClicked(MouseEvent e){
    int x = e.getX() / PSIZE;
    int y = e.getY() / PSIZE;
    int piece = board[x][y];
    if( piece != WALL && piece != SPACE ){
      if( board[x - 1][y] == SPACE ){
        swap_piece( x, y, x - 1, y );
      } else if( board[x + 1][y] == SPACE ){
        swap_piece( x, y, x + 1, y );
      } else if( board[x][y - 1] == SPACE ){
        swap_piece( x, y, x, y - 1 );
      } else if( board[x][y + 1] == SPACE ){
        swap_piece( x, y, x, y + 1 );
      }
      count++;
      message.setText("手数 " + count );
    }
  }

  // 交換 (x2, y2) が SPACE
  void swap_piece( int x1, int y1, int x2, int y2 ){
    board[x2][y2] = board[x1][y1];
    board[x1][y1] = SPACE;
    paint_piece( x1, y1 );
    paint_piece( x2, y2 );
    repaint();
  }

  //ボタンが押されたとき
  public void actionPerformed(ActionEvent e) {
    if( e.getSource() == reset ){
      count = 0;
      message.setText("手数 " + count);
      init_board();
      repaint();
    }
  }
}

