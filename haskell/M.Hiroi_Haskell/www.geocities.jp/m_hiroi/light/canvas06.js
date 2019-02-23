function draw() {
  let c = document.getElementById("can");
  if (!c.getContext) return false;
  let ctx = c.getContext("2d");
  ctx.strokeStyle = 'black';
  ctx.beginPath();
  ctx.moveTo(10, 10);
  ctx.lineTo(390, 10);
  ctx.lineTo(390, 190);
  ctx.lineTo(10, 190);
  ctx.closePath();
  ctx.stroke();

  ctx.strokeStyle = 'blue';
  ctx.beginPath();
  ctx.moveTo(10, 10);
  ctx.quadraticCurveTo(390, 10, 390, 190);
  ctx.stroke();

  ctx.strokeStyle = 'green';
  ctx.beginPath();
  ctx.moveTo(10, 10);
  ctx.quadraticCurveTo(10, 190, 390, 190);
  ctx.stroke();

  ctx.strokeStyle = 'red';
  ctx.beginPath();
  ctx.moveTo(10, 190);
  ctx.quadraticCurveTo(10, 10, 390, 10);
  ctx.stroke();

  ctx.strokeStyle = 'purple';
  ctx.beginPath();
  ctx.moveTo(10, 190);
  ctx.quadraticCurveTo(390, 190, 390, 10);
  ctx.stroke();  
}

window.onload = draw();
