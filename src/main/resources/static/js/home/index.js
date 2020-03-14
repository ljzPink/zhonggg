//给每个p标签设置位置
function setPs() {
    var ps = document.getElementsByTagName('p');
    for (var i = 0; i < ps.length; i++) {
        ps[i].style.transform = 'rotatex(-90deg) rotatey(' + i * 30 + 'deg) translatez(400px)'
    }
}
//鼠标拖动旋转
function move() {
    var zBox = document.querySelector('.z');
    var xBox = document.querySelector('.di');
    //声明计算鼠标移动速度用的变量
    var speedTimer = null;
    var speedX = 0;
    var speedY = 0;
    //声明记录旋转角度的两个变量
    var xDegNow = 90;
    var zDegNow = 0;
    //声明变量记录要旋转的角度
    var xDegDistance = 0;
    var zDegDistance = 0;
    //声明变量记录是否发生鼠标移动事件
    var isMove = false;
    window.onmousedown = function (e) {
        //鼠标按下
        //清除过渡属性
        xBox.style.transition = '';
        zBox.style.transition = '';
        //记录按下的坐标以及计算速度的初始坐标
        var xstart = e.clientX;
        var speedX_start = xstart;
        var ystart = e.clientY;
        var speedY_start = ystart;
        //记录用来计算速度的初始时间
        var tstart = new Date().getTime();
        var tnow = tstart;
        //设置计时器更新计算速度的当时时间
        speedTimer = setInterval(function () {
            tnow = new Date().getTime();
        },10)
        window.onmousemove = function (e) {
            //鼠标移动
            isMove = true;
            //记录当时的坐标计算距离
            var xnow = e.clientX;
            var ynow = e.clientY;
            var xDistance = xnow - xstart;
            var yDistance = ynow - ystart;
            //如果计时超过一定时间（10毫秒），计算速度
            if (tnow - tstart >= 10) {
                //速度=（现在的坐标-初始坐标）/（现在时间-初始时间）
                speedX = (xnow - speedX_start) / (tnow - tstart);
                speedY = (ynow - speedY_start) / (tnow - tstart);
                //让初始时间和坐标等于现在的时间和坐标
                tstart = tnow;
                speedX_start = xnow;
                speedY_start = ynow;
            }
            //把鼠标移动距离计算成角度后设置到页面上
            zDegDistance = zDegNow - (xDistance / 10);
            zBox.style.transform = 'rotatez(' + zDegDistance + 'deg)';
            xDegDistance = xDegNow - (yDistance / 10);
            xDegDistance = xDegDistance > 179 ? 179 : xDegDistance < 1 ? 1 : xDegDistance;
            xBox.style.transform = 'translate(-50%,-50%) rotatex(' + xDegDistance + 'deg)';
        };
    };
    window.onmouseup = function (e) {
        //鼠标松开
        //清除计时器 清除鼠标移动事件
        clearTimeout(speedTimer);
        window.onmousemove = null;
        //判断如果发生移动
        if (isMove) {
            //添加过渡属性
            xBox.style.transition = 'all 0.5s ease-out';
            zBox.style.transition = 'all 0.5s ease-out';
            //根据速度计算目标角度，设置到页面上
            zDegDistance = zDegDistance - (speedX * 10);
            zBox.style.transform = 'rotatez(' + zDegDistance + 'deg)';
            xDegDistance = xDegDistance - (speedY * 10);
            xDegDistance = xDegDistance > 179 ? 179 : xDegDistance < 1 ? 1 : xDegDistance;
            xBox.style.transform = 'translate(-50%,-50%) rotatex(' + xDegDistance + 'deg)';
            //记录当前角度的值更新
            xDegNow = xDegDistance;
            zDegNow = zDegDistance;
            //用到的变量重置
            isMove = false;
            speedX = 0;
            speedY = 0;
            xDegDistance = 0;
            zDegDistance = 0;
        }
    };

}
//鼠标拖拽文件
function drop() {
    //取消鼠标拖拽文件进入窗口的默认行为
    window.ondragover = function (e) {
        e.preventDefault();
    }
    window.ondrop = function (e) {
        e.preventDefault();
    }
    var ps = document.getElementsByTagName('p');
    for (var i = 0; i < ps.length; i++) {
        ps[i].index = i;
        ps[i].ondrop = function (e) {
            var w = new FileReader();
            w.index = this.index;
            w.readAsDataURL(e.dataTransfer.files[0]);
            w.onload = function () {
                ps[this.index].style.backgroundImage = 'url(' + w.result + ')';
                ps[this.index].innerHTML = '';
            }
        }
    }
}