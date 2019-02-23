function draw() {
  let c = document.getElementById("can");
  if (!c.getContext) return false;
  let ctx = c.getContext("2d");
  ctx.strokeStyle = 'red';
  ctx.strokeRect(10, 10, 50, 50);
  ctx.fillStyle = 'green';
  ctx.fillRect(70, 10, 50, 50);
  ctx.fillStyle = 'blue';
  ctx.fillRect(130, 10, 50, 50);
  ctx.clearRect(140, 20, 30, 30);
}

window.onload = draw();
