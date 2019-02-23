//
// ƒ`ƒ‡ƒƒAƒŠ‚Q
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

public class Ant2 extends Applet implements Runnable
{
  // •Ï”’è‹`
  final int SIZE = 150;
  byte plane[] = new byte[SIZE * SIZE];
  int dir = 0, state = 0, x = 75, y = 75, width, height, edge;
  Image double_buffer;
  Graphics dg;
  Thread trig = null;

  // ‰Šú‰»
  public void init(){
    int i;
    trig = new Thread( this );
    width = getSize().width;
    height = getSize().height;
    double_buffer = createImage( width, height );
    dg = double_buffer.getGraphics();
    edge = width / SIZE;

    // ‰æ–Ê‚Ì‰Šú‰»
    dg.setColor( Color.black );
    dg.fillRect( 0, 0, width, height );

    // •½–Ê‚Ì‰Šú‰»
    for( i = 0; i < (SIZE * SIZE); i++ ){
      plane[i] = 0;
    }

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
      doIt();
      doIt();
      doIt();
      doIt();
      repaint();
    }
  }

  void doIt(){
    int postion = y * SIZE + x;
    if( plane[postion] == 0 ){
      dg.setColor( Color.green );
      dg.fillRect( x * edge, y * edge, edge, edge );
      plane[postion] = 1;
      if( state == 0 ){
        dir = (dir + 1) % 4;
      } else {
        dir = (dir + 3) % 4;
        state = 0;
      }
    } else {
      if( state == 0 ){
        dg.setColor( Color.black );
        dg.fillRect( x * edge, y * edge, edge, edge );
        plane[postion] = 0;
        state = 1;
      } else {
        dir = (dir + 3) % 4;
        state = 0;
      }
    }
    switch( dir ){
      case 0 :
        x = (x <= 0 ? SIZE - 1 : x - 1);
        break;
      case 1 :
        y = (y <= 0 ? SIZE - 1 : y - 1);
        break;
      case 2 :
        x = (x >= SIZE - 1 ? 0 : x + 1);
        break;
      case 3 :
        y = (y >= SIZE - 1 ? 0 : y + 1);
        break;
    }
  }
}

