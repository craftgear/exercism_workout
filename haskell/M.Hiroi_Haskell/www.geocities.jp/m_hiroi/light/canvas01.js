function draw() {
  let c = document.getElementById("can");
  if (!c.getContext) return false;
  let ctx = c.getContext("2d");
  ctx.fillRect(10, 10, 180, 180);     // 塗りつぶし
}

window.onload = draw();
