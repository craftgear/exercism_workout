function draw() {
  let c = document.getElementById("can");
  if (!c.getContext) return false;
  let ctx = c.getContext("2d");
  ctx.translate(200, 200);
  for (let z = 0.2; z <= 2.0; z += 0.2) {
    ctx.save();
    ctx.scale(z, 1);
    ctx.beginPath();
    ctx.arc(0, 0, 100, 0, Math.PI * 2);
    ctx.stroke();
    ctx.restore();
    ctx.save();
    ctx.scale(1, z);
    ctx.beginPath();
    ctx.arc(0, 0, 100, 0, Math.PI * 2);
    ctx.stroke();
    ctx.restore();
  }
}

window.onload = draw();