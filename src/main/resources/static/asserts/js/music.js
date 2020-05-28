$(document).ready(function(){
    var title = [[${music.name}]];

  var playlist = [{
      title:title,
      artist:"${music.singer}",
      mp3:"http://47.112.174.9:8088/blogs/temp-rainy/a1ab2b6196c8435e8ec41b5d9b4598ac.mp3",
      //oga:"http://www.jplayer.org/audio/ogg/Miaow-02-Hidden.ogg",
      poster: "/blogs/static/asserts/images/backpic.jpg"
    }
  //   ,{
  //     title:"Cro Magnon Man",
  //     artist:"The Stark Palace",
  //     mp3:"i2.mp3",
  //     //oga:"http://www.jplayer.org/audio/ogg/TSP-01-Cro_magnon_man.ogg",
  //     poster: "/blogs/static/asserts/images/backpic.jpg"
  //   },{
  //     title:"Bubble",
  //     m4a: "Miaow-07-Bubble.m4a",
  //     oga: "Miaow-07-Bubble.ogg",
  //     poster: "/blogs/static/asserts/images/backpic.jpg"
  // }
  ];
  
  var cssSelector = {
    jPlayer: "#jquery_jplayer",
    cssSelectorAncestor: ".music-player"
  };
  
  var options = {
    swfPath: "Jplayer.swf",
    supplied: "ogv, m4v, oga, mp3"
  };
  
  var myPlaylist = new jPlayerPlaylist(cssSelector, playlist, options);
  
});