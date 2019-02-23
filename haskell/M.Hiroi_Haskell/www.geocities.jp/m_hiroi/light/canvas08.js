function draw() {
  let c = document.getElementById("can");
  if (!c.getContext) return false;
  let ctx = c.getContext("2d"),
      img = new Image();
  img.onload = () => {
    ctx.drawImage(img, 0, 0);
    ctx.drawImage(img, 0, 50, img.naturalWidth * 2, img.naturalHeight * 2);
  }
  img.src = "ruby_img/button0.png"
}

window.onload = draw();