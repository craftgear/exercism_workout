//
// InsertSort.java : �P���}���\�[�g�\�[�g�f��
//
//                Copyright (C) 2001 by M.Hiroi
//
// M.Hiroi's Home Page
// URL   : http://www.geocities.co.jp/SiliconValley-Oakland/1680/
// E-Mail: m_hiroi@geocities.co.jp
//

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.*;

// �P���}���\�[�g
public class InsertSort extends Applet implements Runnable, MouseListener
{
  final int START = 0, EXEC = 1, FINISHED = 2;
  int table[];
  int width, height, unit, size, state;
  Image double_buffer;
  Graphics dg;
  Random rand;
  Thread trig = null;
  
  // �V���b�t��
  void shuffle(){
    for( int i = 0; i < size; i++ ){
      int tmp = table[i];
      int p = rand.nextInt() % size;
      if( p < 0 ) p = - p;
      table[i] = table[p];
      table[p] = tmp;
    }
  }

  // ��ʂ̏�����
  void init_image(){
    dg.setColor( Color.lightGray );
    dg.fillRect( 0, 0, width, height );
    dg.setColor( Color.black );
    for( int i = 0; i < size; i++ ){
      dg.drawLine( 0, i * 2, table[i], i * 2 );
    }
  }

  // ������
  public void init(){
    rand   = new Random();
    width  = getSize().width;
    height = getSize().height;
    size   = height / 2;
    unit   = width / size;

    // �_�u���o�b�t�@�̗p��
    double_buffer = createImage( width, height );
    dg = double_buffer.getGraphics();

    // �}�E�X����
    addMouseListener( this );

    // �f�[�^�̏�����
    table = new int [size];
    for( int i = 1; i < size; i++ ) table[i] = i * unit;
    shuffle();
    init_image();
    state = START;
  }

  public void paint( Graphics g ){
    g.drawImage( double_buffer, 0, 0, null );
  }

  public void update( Graphics g ){
    paint( g );
  }

  public void run(){
    while( true ){
      insertsort();
      state = FINISHED;
      trig.suspend();  // �X���b�h�ꎞ��~
    }
  }

  public void destroy(){
    trig.stop();
  }

  // �f�[�^��`��
  void draw_data( int n ){
    int m = n * 2;
    dg.setColor( Color.lightGray );
    dg.drawLine( 0, m, width, m );
    dg.setColor( Color.black );
    dg.drawLine( 0, m, table[n], m );
  }
  
  // �����ʒu������
  void delete_search_line( int n ){
    if( n >= 0 ){
      int m = n * 2 + 1;
      dg.setColor( Color.lightGray );
      dg.drawLine( 0, m, width, m );
    }
  }

  // �����ʒu��`��
  void draw_search_line( int n, Color c ){
    if( n >= 0 ){
      int m = n * 2 + 1;
      dg.setColor( c );
      dg.drawLine( 0, m, width, m );
    }
  }

  // �P���}���\�[�g
  void insertsort(){
    int p1 = -1, p2 = -1;
    for( int i = 1; i < size; i++ ){
      for( int j = i - 1; j >= 0; j-- ){
        int tmp;
        // �x�~
        try {
          Thread.sleep( 50 );
        } catch( InterruptedException e ){}
        
        // �������C���̕`��
        delete_search_line( p1 );
        draw_search_line( i, Color.blue );
        delete_search_line( p2 );
        draw_search_line( j, Color.red );
        p1 = i;
        p2 = j;
        
        if( table[j + 1] > table[j] ){
          repaint();
          break;
        }
        tmp = table[j + 1];
        table[j + 1] = table[j];
        table[j] = tmp;
        
        // �f�[�^�̕`��
        draw_data( j );
        draw_data( j + 1 );
        repaint();
      }
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
        shuffle();
        init_image();
        state = EXEC;
        trig.resume();    // �X���b�h�ĊJ
      }
    }
  }
}

