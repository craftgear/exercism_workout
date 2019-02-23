function draw() {
  let c = document.getElementById("can");
  if (!c.getContext) return false;
  let ctx = c.getContext("2d");
  ctx.beginPath();
  ctx.arc(50, 50, 50, 0, Math.PI * 2);
  ctx.stroke();

  ctx.fillStyle = 'green';
  ctx.beginPath();
  ctx.arc(150, 50, 50, 0, Math.PI * 2);
  ctx.fill();

  ctx.strokeStyle = 'red';
  ctx.lineWidth = 4;
  ctx.beginPath();
  ctx.arc(250, 50, 50, 0, Math.PI * 1.5);
  ctx.closePath();
  ctx.stroke();

  ctx.fillStyle = 'blue';
  ctx.beginPath();
  ctx.arc(350, 50, 50, 0, Math.PI);
  ctx.fill();
}

window.onload = draw();
