function draw() {
  let c = document.getElementById("can");
  if (!c.getContext) return false;
  let ctx = c.getContext("2d");
  for (let i = 0; i < 10; i++) {
    ctx.lineWidth = i + 1;
    ctx.beginPath();
    ctx.moveTo(10, 10 + i * 20);
    ctx.lineTo(380, 10 + i * 20);
    ctx.stroke();
  }
}

window.onload = draw();
