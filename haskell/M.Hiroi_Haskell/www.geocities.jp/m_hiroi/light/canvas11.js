function draw() {
  let c = document.getElementById("can");
  if (!c.getContext) return false;
  let ctx = c.getContext("2d");
  ctx.fillStyle = "rgba(255, 0, 0, 0.5)";
  ctx.translate(200, 200);
  for (let x = 0; x < 12; x++) {
    ctx.fillRect(0, 0, 200, 25);
    ctx.rotate(Math.PI / 6);
  }
}

window.onload = draw();