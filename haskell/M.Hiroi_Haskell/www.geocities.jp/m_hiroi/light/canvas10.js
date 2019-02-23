function draw() {
  let c = document.getElementById("can");
  if (!c.getContext) return false;
  let ctx = c.getContext("2d");
  ctx.fillStyle = "rgba(0, 255, 0, 0.5)";
  for (let x = 0; x < 10; x++) {
    ctx.fillRect(0, 0, 100, 100);
    ctx.translate(30, 30);
  }
}

window.onload = draw();