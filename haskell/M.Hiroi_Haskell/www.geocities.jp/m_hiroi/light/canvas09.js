function draw() {
  let c = document.getElementById("can");
  if (!c.getContext) return false;
  let ctx = c.getContext("2d"),
      grd1 = ctx.createLinearGradient(10,10,10,190),
      grd2 = ctx.createLinearGradient(70,10,390,10),
      grd3 = ctx.createLinearGradient(70,80,180,190),
      grd4 = ctx.createLinearGradient(190,190,300,80);
  grd1.addColorStop(0, 'blue');
  grd1.addColorStop(0.5, 'yellow');
  grd1.addColorStop(1, 'red');
  ctx.fillStyle = grd1;
  ctx.fillRect(10, 10, 50, 180);

  grd2.addColorStop(0, 'red');
  grd2.addColorStop(0.5, 'yellow');
  grd2.addColorStop(1, 'blue');
  ctx.fillStyle = grd2;
  ctx.fillRect(70, 10, 320, 60);

  grd3.addColorStop(0, 'blue');
  grd3.addColorStop(0.3, 'cyan');
  grd3.addColorStop(0.7, 'yellow');
  grd3.addColorStop(1, 'red');
  ctx.fillStyle = grd3;
  ctx.fillRect(70, 80, 110, 110);

  grd4.addColorStop(0, 'blue');
  grd4.addColorStop(0.3, 'cyan');
  grd4.addColorStop(0.7, 'yellow');
  grd4.addColorStop(1, 'red');
  ctx.fillStyle = grd4;
  ctx.fillRect(190, 80, 110, 110);

  let grd5 = ctx.createRadialGradient(150,250,10,200,300,100);
  grd5.addColorStop(0, 'red');
  grd5.addColorStop(0.5, 'yellow');
  grd5.addColorStop(1, 'blue');
  ctx.fillStyle = grd5;
  ctx.fillRect(10, 200, 380, 180);
}

window.onload = draw();