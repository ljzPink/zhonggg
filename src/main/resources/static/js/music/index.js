$(function(){
    /**
     var music_list = [
     {
            "id":0,
            "name" : "稻香",
            "author" : "周杰伦",
            "src" : "/static/music/周杰伦 - 稻香.mp3",
            "lyric":[]
        }
     ]
     **/
    var audio = document.getElementById("my-audio"); //audio
    var music_list = []  //歌曲列表信息
    var play_or_pause_btn = $(".icon-play-pause")  //播放或暂停按钮
    var music_overall_time = 0//音乐总时长
    var music_current_time  = 0//音乐当前播放的位置
    var time_id //定时器id
    var slid = $(".my-progress .slid")  //进度条定位点
    var active_progress = $("#active-progress")  //进度条
    var my_progress = $(".my-progress")  //进度条的容器
    var my_progress_width = my_progress.width()  //进度条的容器的宽
    var slid_left = 0  //进度条定位点的left值
    var active_progress_width = 0  //进度条的宽
    var song_words_container_height = 0 //歌词滚动容器的高度
    var song_words_container_p_height = 0 //每行歌词的高度
    var song_words_time_list = [] //用来存放每行歌词的时间
    console.log(music_overall_time,slid_left,active_progress_width,my_progress_width)

    //修改歌曲信息的地方(歌曲标题,歌曲时间)
    function show_info(song_name,author){
        $(".song-name span").html(song_name)
        $(".song-name1").html(song_name)
        $(".author").html(author)
        $(".progress-top-title p").html(song_name)
    }
    //将歌词的时间转化成 秒  00:00.50 -> 0.05s
    function lyric_time_to_s(lyric_time){
        var m = lyric_time.split(":")[0] //截取分
        var s = lyric_time.split(".")[0].split(":")[1] //截取秒
        var ms = lyric_time.split(".")[1] // 截取毫秒
        ss = parseInt(m)*60 + parseInt(s) + parseInt(ms)/1000 //换算成秒
        return ss
    }
    //获取歌曲歌词的函数
    function get_music_lyric(id){
        $.ajax({
            url:'get_music_lyric?id='+id,
            type:'get',
            async:false, //改为同步
            success:function (a) {
                var data = JSON.parse(a)
                var data_title = data.splice(0,2) //把前面两个没有时间的删除掉
                //console.log(data_title)
                //console.log(data)
                var html = ''
                var reg = /\d+:\d+\.\d+/
                song_words_time_list = [] //先清空存放歌词的数组
                //只把歌词显示出来
                for( var i =0;i<data.length;i++){
                    var lyric_time = reg.exec(data[i])[0] //用正则匹配取出歌词的时间00:00.50。。。。
                    var times = lyric_time_to_s(lyric_time)
                    song_words_time_list.push(times) //再把歌词时间存放到数组中
                    html += `
                        <p data-time=${times}>${data[i].split("]")[1]}</p>
                     `
                }
                $(".song-words-container").html(html)
            }
        })
    }
    //获取歌曲列表
    $.ajax({
        url:'getSongs',
        type:'post',
        success:function (data) {
            var data = JSON.parse(data)
            music_list = data
            var music_menu = '' //左边音乐菜单
            for(var i=0;i<music_list.length;i++){
                music_menu += `<li data-number=${music_list[i].id} class="${music_list[i].id==0?"active":""}">${music_list[i].name}</li>`
            }
            $(".music-list ul").html(music_menu) //生成左边菜单,默认选择第一个
            audio.src = music_list[0].src //默认选择第一个的src
            $(audio).attr("data-number",0) //默认选择第一个的id,用来做标记的编号
            show_info(music_list[0].name,music_list[0].author) //默认传递第一个的
            //默认获取第一首歌曲的歌词
            get_music_lyric(0)

        }
    })

    //获取播放、暂停按钮的状态
    function get_play_or_pause_btn_status() {
        if(play_or_pause_btn.hasClass("glyphicon-play")){ //按钮是播放样子,但是音乐是暂停状态
            return "pause"
        }else if(play_or_pause_btn.hasClass("glyphicon-pause")){ //按钮是暂停样子,但是音乐是播放状态
            return "play"
        }else {
            return "error"
        }
    }
    //控制音乐进行播放或者暂停
    function control_music(pop){
        if(pop == "play" ){ //如果是播放状态就让它暂停
            clearInterval(time_id)
            audio.pause()
            play_or_pause_btn.removeClass("glyphicon-pause").addClass("glyphicon-play")
        }else if(pop == "pause"){//如果是暂停状态就让它播放
            monitor_music()
            audio.play()
            play_or_pause_btn.removeClass("glyphicon-play").addClass("glyphicon-pause")
        }else {
            clearInterval(time_id)
            audio.pause()
            alert("error")
        }
    }
    //控制歌词滚动的函数
    function my_change_words(){
        //console.log(music_current_time,song_words_container_height,song_words_container_p_height,song_words_time_list)
        var music_current_words_id = 0 //当前活动的歌词的id
        var move_top = 0 //歌词容器移动的top
        for(var i=0;i<song_words_time_list.length;i++){ //遍历存放歌词时间的数组
            if(song_words_time_list[i] > music_current_time){ //找到歌词时间数组中第一个大于歌曲的当前时间的时间的i
                music_current_words_id = i - 1 < 0? 0 : i - 1 //第一个大于当前歌曲时间的前一个时间 和 当前歌曲时间匹配
                var song_words_top = music_current_words_id * song_words_container_p_height //当前歌词的top
                //通过当前时间的下标和歌词的高度 可以算出 歌词容器向上移动的top
                if(music_current_words_id > 5){ //当前歌词大于5行时候才让歌词移动
                    move_top = song_words_top - song_words_container_p_height*5
                }
                //歌词容器向上移动的函数
                $(".song-words-container").animate({top:- move_top+"px"},
                    500,"linear",function () {
                        //改变当前歌词的样式
                        $(this).children().removeClass("playOver").eq(music_current_words_id).addClass("playOver")
                    })
                break //找到歌词时间数组中第一个大于歌曲的当前时间的时间的i 就跳出for循环
            }else if(song_words_time_list[song_words_time_list.length-1] < music_current_time){ //如果歌词最后的时间小于歌曲当前时间
                //把歌词移动到最后
                $(".song-words-container").animate({top:- ($(".song-words-container").height()-song_words_container_p_height*5)+"px"},
                    500,"linear",function () {
                        //改变当前歌词的样式
                        $(this).children().removeClass("playOver").eq(-1).addClass("playOver")
                    })
                return
            }
        }
    }
    //让进度条变长或变短,//让进度条上的定位点移动
    function control_active_progress(){
        active_progress_width = parseFloat((music_current_time/music_overall_time)*100).toFixed(2)+"%"
        //console.log(active_progress_width)
        active_progress.css("width",active_progress_width)
        slid_left = parseFloat((music_current_time/music_overall_time)*100).toFixed(2)+"%"
        slid.css("left",slid_left)
    }
    //秒转化为分的函数
    function s_to_hs(s){
        //算法：将秒数除以60，然后下舍入，既得到分钟数
        var h;
        h  =   Math.floor(s/60);
        //计算秒
        //算法：取得秒%60的余数，既得到秒数
        s  =   parseInt( s%60 );
        //将变量转换为字符串
        h    +=    '';
        s    +=    '';
        //如果只有一位数，前面增加一个0
        h  =   (h.length==1)?'0'+h:h;
        s  =   (s.length==1)?'0'+s:s;
        return h+':'+s;
    }
    //改变进度条上面的显示时间的函数
    function my_change_time(all_time,current_time) {
        var new_current_time = s_to_hs(current_time)
        //console.log(new_current_time,all_time)
        $(".progress-top-time p").html(new_current_time+"/"+all_time)

    }
    //监听音乐的定时器**********************
    function monitor_music(){
        console.log(music_overall_time)
        console.log(song_words_container_p_height)
        var all_time = s_to_hs(music_overall_time) || "00:00"
        time_id = setInterval(function () {
            //console.log(time_id)
            music_current_time = audio.currentTime
            control_active_progress() //控制进度条和滑块
            my_change_time(all_time,music_current_time) //控制显示时间
            my_change_words() //控制歌词滚动
        },1000)
    }
    // 暂停，播放按钮
    $(".control-left").on("click",".icon-play-pause",function (){ // 暂停，播放按钮的点击事件
        music_overall_time = audio.duration; //获取音乐总时长
        var pop = get_play_or_pause_btn_status()
        console.log(pop)
        song_words_container_height = $(".song-words-container").height() //获取歌词滚动容器的高度
        song_words_container_p_height = $(".song-words-container p").height() //获取每行歌词的高度
        control_music(pop)
    })
    //点击播放列表、上一首、下一首按钮的 切换歌曲函数
    function change_music_play(audio_obj){ //audio_obj 歌曲对象
        clearInterval(time_id)//清除定时器
        audio.src = audio_obj.src //修改audio的scr
        $(audio).attr("data-number",audio_obj.id) //修改audio的歌曲编号
        show_info(audio_obj.name,audio_obj.author) //修改显示信息
        get_music_lyric(audio_obj.id) //加载歌曲的歌词

        audio.addEventListener("loadeddata", function(){ //监听音乐是否加载完毕
            clearInterval(time_id)//清除定时器
            music_overall_time = audio.duration; //获取音乐总长
            control_music("pause") //传个pause状态给播放函数，开始播放
        });
        //修改歌曲列表的样式,将当前切换的歌曲加上active的class
        var lis = $(".music-list ul li")
        lis.removeClass("active")
        for(var i=0;i<lis.length;i++){
            if($(lis[i]).attr("data-number") == audio_obj.id){
                $(lis[i]).addClass("active")
            }
        }
    }
    //列表点击播放
    $(".music-list ul").on("click","li",function () {
        var click_list_number= parseInt($(this).attr("data-number")) //当前点击的歌曲的编号
        var audio_data_number = parseInt($(audio).attr("data-number"))  //当前正在播放或者暂停歌曲的编号
        var pop = get_play_or_pause_btn_status() // 获取歌曲状态
        //console.log(click_list_number,audio_data_number,pop)
        song_words_container_height = $(".song-words-container").height() //获取歌词滚动容器的高度
        song_words_container_p_height = $(".song-words-container p").height() //获取每行歌词的高度
        if((audio_data_number == click_list_number)&&(pop=="pause")){  //编号相等,是同一首歌曲,暂停状态，就播放
            console.log($(".song-words-container").height(),$(".song-words-container").width())
            music_overall_time = audio.duration; //获取音乐总长
            control_music(pop)
        }else if(audio_data_number != click_list_number){ //如果编号不相同(点击歌曲和当前播放歌曲不一致)
            //在歌曲列表中获取和当前点击的歌曲编号相同的歌曲对象
            var  audio_obj = music_list.find(x => x.id == click_list_number)
            change_music_play(audio_obj) //把获取的歌曲对象传给切歌函数
        }
        //最后修改当前点击的样式
        $(this).addClass("active").siblings().removeClass("active")
    })
    //上一首&下一首按钮点击事件
    function next_or_prev(to) {
        var audio_data_number = parseInt($(audio).attr("data-number"))  //当前正在播放或者暂停歌曲的编号
        console.log(audio_data_number)
        song_words_container_height = $(".song-words-container").height() //获取歌词滚动容器的高度
        song_words_container_p_height = $(".song-words-container p").height() //获取每行歌词的高度
        if(to == "prev"){
            //在歌曲列表中获取当前播放歌曲编号-1的歌曲对象
            var  audio_obj = music_list.find(x => x.id == audio_data_number-1)
            change_music_play(audio_obj) //把获取的歌曲对象传给切歌函数
        }else if(to == "next"){
            //在歌曲列表中获取当前播放歌曲编号-1的歌曲对象
            var  audio_obj = music_list.find(x => x.id == audio_data_number+1)
            change_music_play(audio_obj) //把获取的歌曲对象传给切歌函数
        }else {
            return
        }
    }
    //点击上一首按钮
    $(".control-left").on("click",".icon-prev",function () {
        next_or_prev("prev")
    })
    //点击下一首按钮
    $(".control-left .icon-next").on("click",()=>{
        next_or_prev("next")
    })
    //滑块定位点的拖拽播放音乐事件
    $(".my-progress .slid").on({
        "mousedown":function (e) { //鼠标按下事件
            e.preventDefault()
            clearInterval(time_id)  //清除定时器
            var this_ = this
            console.log("down")
            var this_parent_w = my_progress_width //进度条容器的宽度
            var start_this_x = parseFloat($(this).css("left")) //刚开始滑块的left值
            var end_this_x = start_this_x //滑动结束后滑块的left值(如果不滑动就等于起始值)
            var start_e_x = e.clientX  // 鼠标的x坐标
            var e_min = my_progress.offset().left //进度条容器的左边的坐标
            var e_max = my_progress.offset().left + this_parent_w //进度条容器的右边的坐标
            console.log(start_this_x,e_min,e_max,start_e_x)

            $(document).on("mousemove",function (e) { //触发鼠标移动时间
                e.preventDefault()
                var end_e_x = e.clientX  // 鼠标的x坐标
                if(end_e_x >= e_min && end_e_x <= e_max){ //如果鼠标移动范围在进度条容器范围内
                    console.log("parent move!")
                    end_this_x = start_this_x+(end_e_x - start_e_x) // 获取滑块最后的left值 = 起始值+（滑块移动的距离）
                    if(end_this_x < 0){end_this_x = 0} //滑块最后的left值 超出进度条的容器范围
                    if(end_this_x > this_parent_w){end_this_x = this_parent_w} //滑块最后的left值 超出进度条的容器范围
                    $(this_).css("left",end_this_x+"px") //设置滑块的left值
                    active_progress.css("width",end_this_x+"px") //设置活动进度条的宽度
                }
            })
            $(document).on("mouseup",function () {
                $(this).off("mousemove") //解绑鼠标滑动事件
                // 音乐当前位置播放时间 = 总时间(滑块当前位置/进度条总长)
                music_current_time = music_overall_time*(end_this_x/this_parent_w)
                audio.currentTime = music_current_time //设置歌曲当前播放时间
                control_music(pop="pause") //把pause 状态传给播放函数，重新开始播放
                console.log(end_this_x)
                $(this).off("mouseup") // 解绑鼠标抬起事件
            })
        }
    })
    //点击进度条实现音乐快进，后退播放事件
    $("#position-progress").on("click",function(e){
        e.preventDefault()
        clearInterval(time_id)  //清除定时器
        var this_parent_w = parseFloat($(this).parent().width()) //进度条容器的宽度
        var this_parent_l = $(this).parent().offset().left //进度条容器的左边的坐标
        var start_e_x = e.clientX  // 鼠标点击位置的x坐标
        var position_x = start_e_x - this_parent_l // 鼠标点击位置到进度条左边的距离
        console.log(this_parent_l,this_parent_w,start_e_x,position_x)
        // 音乐当前位置播放时间 = 总时间(鼠标点击位置到进度条左边的距离/进度条总长)
        music_current_time = music_overall_time*(position_x/this_parent_w)
        audio.currentTime = music_current_time //设置歌曲当前播放时间
        slid.css("left",position_x+"px") //设置滑块left
        active_progress.css("width",position_x+"px") //设置活动进度条宽
        control_music(pop="pause") //把pause 状态传给播放函数，重新开始播放
    })
})
