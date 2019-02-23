function draw() {
  let c = document.getElementById("can");
  if (!c.getContext) return false;
  let ctx = c.getContext("2d");
  ctx.translate(200, 200);
  ctx.beginPath();
  ctx.arc(0, 0, 100, 0, Math.PI * 2);
  ctx.stroke();

  ctx.scale(2, 1);
  ctx.beginPath();
  ctx.arc(0, 0, 100, 0, Math.PI * 2);
  ctx.stroke();

  ctx.scale(0.5, 2);
  ctx.beginPath();
  ctx.arc(0, 0, 100, 0, Math.PI * 2);
  ctx.stroke();

}

window.onload = draw();