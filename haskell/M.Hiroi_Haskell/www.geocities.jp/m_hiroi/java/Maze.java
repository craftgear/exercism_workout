//
// Maze.java : ���H�̐����Ɖ�@�̃f��
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
  // �ϐ���`
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

  // ������
  public void init(){
    width  = getSize().width;
    height = getSize().height;
    xmax = width / 4;             // �����ɂȂ邱��
    ymax = height / 4;            // ����
    maxsite = xmax * ymax / 4;
    map = new byte [xmax + 1][ymax + 1];
    xx  = new int [maxsite];
    yy  = new int [maxsite];
    double_buffer = createImage( width, height );
    dg = double_buffer.getGraphics();
    rand = new Random();
    state = START;

    // �}�E�X����
    addMouseListener( this );

    // ��ʂ̏�����
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

  // 0 ���� n - 1 �܂ł̗����𐶐�����
  int get_rand( int n ){
    int r = rand.nextInt() % n;
    if( r < 0 ) r = -r;
    return r;
  }

  // �}�b�v�̕`��
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
  
  // �}�b�v�̏�����
  void init_map(){
    // ������
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
    // �T�C�g��������
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

  // �}�b�v�̐���
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
            // �x�~
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

  // ���H������
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
        // �ċA
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
      trig.suspend();  // �X���b�h�ꎞ��~
    }
  }

  // �}�E�X�̃C�x���g����
  public void mouseEntered(MouseEvent e){};
  public void mouseExited(MouseEvent e){};
  public void mousePressed(MouseEvent e){};
  public void mouseReleased(MouseEvent e){};

  // �}�E�X�N���b�N
  public void mouseClicked(MouseEvent e){
    // �X�^�[�g
    if( trig == null ){
      trig   = new Thread( this );
      trig.start();
    } else {
      if( state == FINISHED ){
        state = EXEC;
        trig.resume();    // �X���b�h�ĊJ
      }
    }
  }
}

