window.onload=function(){
	// 匿名函数
	document.getElementById("last").onclick=goLast;
	document.getElementById("next").onclick=goNext;
	document.getElementById("prev").onclick=goPrev;
	document.getElementById("first").onclick=goFirst;
}
function goFirst()
{
	window.location.href='viewRole.do?currentPage=1';// 发出GET请求
}
function goNext()
{
	window.location.href='viewRole.do?currentPage='+(currentPage+1);
}
function goPrev()
{
	window.location.href='viewRole.do?currentPage='+(currentPage-1);
}
function goLast()
{
	window.location.href='viewRole.do?currentPage='+totalPage;
}