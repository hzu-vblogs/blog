$(document).ready(function(){
	var arr=[];
	var data1={
		userid:1,
		album:"12",
		picture:[
			{url:"11",description:"nihao"},
			{url:"22",description:"wobuhao"}],
		};
		var txt1="<div>11</div>";
		var txt2="<div class='showimg'><img class='card-img-top' style='width: 100%; display: block;' src='images/13.jpg' data-holder-rendered='true'></div>"
	$.each(data1,function(i,album){
		if(i=='picture'){
		$.each(album,function(index,value){
			$("#testbox").append(txt1,txt2);
		})
		}
});
console.log(arr);
});