function draw() {
  let c = document.getElementById("can");
  if (!c.getContext) return false;
  let ctx = c.getContext("2d");
  ctx.strokeStyle = 'red';
  ctx.beginPath();
  ctx.moveTo(10, 10);
  ctx.lineTo(380, 10);
  ctx.lineTo(10, 180);
  ctx.closePath();
  ctx.stroke();
 
  ctx.fillStyle = 'green';
  ctx.beginPath();
  ctx.moveTo(390, 10);
  ctx.lineTo(10, 190);
  ctx.lineTo(390, 190);
  ctx.fill();
}

window.onload = draw();
