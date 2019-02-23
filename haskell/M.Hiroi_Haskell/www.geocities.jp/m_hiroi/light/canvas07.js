function draw() {
  let c = document.getElementById("can");
  if (!c.getContext) return false;
  let ctx = c.getContext("2d");
  ctx.font = "48px serif";
  ctx.beginPath();
  ctx.fillText("hello, world", 10, 50);

  ctx.beginPath();
  ctx.strokeText("hello, world", 10, 100);
}

window.onload = draw();
