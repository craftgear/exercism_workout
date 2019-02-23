function draw() {
  let c = document.getElementById("can");
  if (!c.getContext) return false;
  let ctx = c.getContext("2d");
  ctx.shadowColor = "gray";
  ctx.shadowBlur = 2;
  ctx.shadowOffsetX = 5;
  ctx.shadowOffsetY = 5;

  ctx.fillStyle = "green";
  ctx.fillRect(10, 10, 200, 200);

  ctx.font = "48px serif";
  ctx.fillStyle = "black";
  ctx.beginPath();
  ctx.fillText("hello, world", 10, 280);

  let img = new Image();
  img.onload = () => {
    ctx.drawImage(img, 10, 300);
  }
  img.src = "ruby_img/button0.png"
}

window.onload = draw();